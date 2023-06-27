<template>
  <div class="bodyPad">

    <div class="mainPad">
      <div class="headPad">
        <div class="m-4">
          <div class="text-center text-xl">商家信息</div>
        </div>

      </div>

      <div class="businessintroduce  ">
        <img :src=business.img>
        <div class="antialiased font-black text-2xl">{{ business.businessName }}</div>
        <div class="text-xl text-yellow-600">￥{{ business.starPrice }}起送|￥{{ business.deliveryPrice }}配送</div>
        <div class="text-sm">{{ business.businessExplain }}</div>
      </div>


      <div class="foodList">
        <div class="foodCard" v-for="item in food">
          <div class="foodIcon">
            <img :src=item.img>
          </div>
          <div class="foodInfo">
            <div>{{ item.foodName }}</div>
            <div>
              <div class="flex text-center gap-16">
                <div class="flex w-35" >
                  {{ item.foodExplain }}￥{{ item.foodPrice }}
                </div>
                <div class="flex-none">
                  <p class="text-white bg-red-400 m-1">{{ item.remarks }}</p>
                </div>

              </div>
              <div class="flex text-center gap-6">
                <div class="flex w-35" >
                  推荐指数：
                  <i class="fas fa-star"></i>
                  <i class="fas fa-star"></i>
                  <i class="fas fa-star"></i>
                  <i class="fas fa-star"></i>
                  <i class="fas fa-star"></i>
                </div>
                <div class="flex-none">
                  <p><i class="fas fa-minus-circle" style="color:grey" @click="item.quantity=minus(item.quantity,item.foodPrice)"></i>
                    &nbsp;&nbsp;{{ item.quantity }}&nbsp;&nbsp;
                    <i class="fas fa-plus-circle" style="color:dodgerblue" @click="add(item.quantity,item.foodPrice);item.quantity++"></i>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="barPad">

      <div class="buttonPad  flex flex-row">

        <a class="btn btn-full btn-flat btn-black basis-8/12">
          <i class="fal fa-shopping-cart" style="color:white"></i>
          <div class="cart-left-icon-quantity">{{ count }}</div>
          <div>总共：￥{{ total }}(另需配送费{{ business.deliveryPrice }}元)</div>
        </a>
        <a class="btn btn-full btn-flat btn-green basis-4/12 text-xl" @click="order()">
          <i class="fas fa-sign-in-alt"></i>
          去结算
        </a>
      </div>
    </div>

  </div>
</template>

<script setup>
import {useRouter} from "vue-router";
import axios from "axios";
import {onMounted, ref} from "vue";
import cacheService from "../provider/cacheService";

const router = useRouter()
//加载食物列表和商家信息
const food = ref([])
const business = ref([])
const getFoodList = () => {
  const data = {
    "id": cacheService.get('businessId'),
  }
  axios.post('/food/findbybusiness',data,{
        headers: {
          'Authorization': "Bearer " + cacheService.get('token'),
          'Content-Type': 'multipart/form-data'
        }
      }
  )
      .then(function (res) {
        console.log(res.data)
        food.value = res.data
        for(let i=0; i<res.data.length;i++){//为每个食物对象新增数量属性
          res.data[i].quantity = 0  //添加quantity属性并设置为false
          // console.log(res.data[i])
        }
      })
  axios.post('/business/findbyid',data,{
        headers: {
          'Authorization': "Bearer " + cacheService.get('token'),
          'Content-Type': 'multipart/form-data'
        }
      }
  )
      .then(function (res) {
        console.log(res.data)
        business.value = res.data
        cacheService.add('businessName',res.data.businessName)
        cacheService.add('deliveryPrice',res.data.deliveryPrice)
      })
}
//初始化数据函数
onMounted(async () => {
  await getFoodList()
})

//计算总数量和总价
const count = ref(0)//总数量
const total = ref(0)//总价
const minus = (quantity, price) => {
  if(cacheService.get('token')!=null){
    if(quantity>0){
      total.value -= price
      count.value--
      return --quantity
    }
    return 0
  }else {
    alert("请先登录")
    router.push('/login')
  }
}
const add = (quantity, price) => {
  if(cacheService.get('token')!=null){
    total.value += price
    count.value++
  }else {
    alert("请先登录")
    router.push('/login')
  }
}

//创建订单
const order = () => {
  if(cacheService.get('token')==null){
    alert("请先登录")
    router.push('/login')
    return
  }
  const data = {
    "bid": cacheService.get('businessId'),
    "uid": cacheService.get('userid'),
    "total": total.value + business.value.deliveryPrice,
    "daid": 1
  }
  if(total.value>=business.value.starPrice){
    axios.post('/orders/add',data,{
          headers: {
            'Authorization': "Bearer " + cacheService.get('token'),
            'Content-Type': 'multipart/form-data'
          }
        })
        .then(function (res) {
          for (let i = 0; i < food.value.length; i++){
            const data2 = {
              "oid": res.data.id,
              "fid": food.value[i].id,
              "quantity": food.value[i].quantity,
            }
            if (food.value[i].quantity !== 0){
              //数量不为空的食物添加到订单细节
              axios.post('/orderDetailet/add',data2,{
                    headers: {
                      'Authorization': "Bearer " + cacheService.get('token'),
                      'Content-Type': 'multipart/form-data'
                    }
                  })
                  .then(function (res) {
                    console.log(res.data)
                    cacheService.add('total',total.value)//缓存总价格
                    cacheService.add('count',count.value)//缓存总数量
                    let detailet = {//缓存的订单细节
                      "img": food.value[i].img,
                      "foodName": food.value[i].foodName,
                      "price": food.value[i].foodPrice,
                      "fid": res.data.foodId
                    }
                    console.log(detailet)
                    let key = 'detailet'+res.data.foodId//某个食物
                    cacheService.add(key,JSON.stringify(detailet))//转成字符串存储
                  })
            }
          }
          cacheService.add('orderId',res.data.id)
          router.push('/order')
        })
  }
  else {
    alert("价格不足起送费")
  }
}
</script>

<style scoped>
@import "../style/css/businessInf.css";
@import "https://pro.fontawesome.com/releases/v5.10.0/css/all.css";
.cart-left-icon-quantity {
  width: 20px;
  height: 20px;
  border-radius: 60px;
  background-color: red;
  color: #fff;
  font-size: 10px;

  display: flex;
  justify-content: center;
  align-items: center;

  /*position: absolute;*/
  /*right: -1.5vw;*/
  /*top: -1.5vw;*/
}
</style>