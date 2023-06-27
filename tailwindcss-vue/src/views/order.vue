<template>
  <div class="bodyPad">

    <div class="mainPad">
      <div class="headPad">
        <div class="m-2 text-white">
          <div class="text-center text-xl">确认订单</div>
        </div>
        <span ></span>
        <div class="p-2">
          <div class="text-left text-sm m-0.5">订单配送至</div>
          <div class="text-left p-0.5 semibold">昆明市呈贡区云南大学软件学院1203 <i class="fas fa-angle-right text-right"></i></div>
          <div class="text-left text-sm mx-0.5">{{ cacheService.get('userid') }} 12345678900</div>
        </div>

      </div>

      <div class="businessname">
        <!--            <img src="images/饺子店图.jpg">-->
        <div class="antialiased font-black text-base text-center my-1">{{ cacheService.get('businessName') }}</div>
        <span></span>
        <!--            <div class="text-xl text-yellow-600">￥15起送|￥3配送</div>-->
        <!--            <div class="text-sm">结合现代健康饮食理念，精选上好食材，为顾客提供真材实料、现包现煮的美味健康水饺。</div>-->
      </div>


      <div class="foodList">
        <div class="foodCard" v-for="item in order">
          <div class="foodIcon">
            <img :src=item.img>
          </div>
          <div class="foodInfo">
            <!--                    <div>蟹黄饺子(蒸饺）</div>-->
            <div>
              <div class="flex text-center gap-24">
                <div class="flex w-35" >
                  {{ item.foodName }}
                </div>
                <div class="flex-none">
                  <p class=" m-1 text-base">￥{{ item.price }}</p>
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
                  <p><i class="fal fa-times" style="color:black">&nbsp;</i><i class="fas fa-minus-circle" style="color:grey"></i>&nbsp;&nbsp;{{ item.quantity }}&nbsp;&nbsp;<i class="fas fa-plus-circle" style="color:dodgerblue"></i></p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <span></span>
        <div class="freightCard">
          <div class="freightInfo">
            <div class="flex text-center gap-x-64">
              <div class="flex w-2/3 ml-1" >
                配送费
              </div>
              <div class="flex-none">
                <p class="text-base m-1">￥{{ cacheService.get('deliveryPrice') }}</p>
              </div>

            </div>

          </div>
        </div>
        <span></span>

      </div>
    </div>

    <div class="barPad">

      <div class="buttonPad  flex flex-row" >

        <a class="btn btn-full btn-flat btn-black basis-8/12">
          <i class="fal fa-shopping-cart" style="color:white"></i>
          <div class="cart-left-icon-quantity">{{ cacheService.get('count') }}</div>
          <div>总共：￥{{ cacheService.get('total') }}(另需配送费{{ cacheService.get('deliveryPrice') }}元)</div>
        </a>
        <a class="btn btn-full btn-flat btn-green basis-4/12 text-xl"  @click="payment()">
          <i class="fas fa-sign-in-alt"></i>
          去支付
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
const payment = () => {
  router.push('/payment')
}
//加载订单细节
const order = ref([])
const getOrderList = () => {
  const data = {
    "oid": cacheService.get('orderId'),
  }
  axios.post('/orderDetailet/findbyorders',data,{
        headers: {
          'Authorization': "Bearer " + cacheService.get('token'),
          'Content-Type': 'multipart/form-data'
        }
      }
  )
      .then(function (res) {
        for(let i = 0; i < res.data.length; i++){
          let key = 'detailet'+res.data[i].foodId
          let detailet = JSON.parse(cacheService.get(key))//将字符串转为json对象
          res.data[i] = Object.assign(res.data[i],detailet)//合并两个json格式
          console.log(detailet)
        }
        console.log(order.value = res.data)
        cacheService.add('pay',JSON.stringify(order.value))//转成字符串存储
      })
}
//初始化数据函数
onMounted(async () => {
  await getOrderList()
})
</script>

<style scoped>
@import "../style/css/order.css";
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