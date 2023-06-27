<template>
  <div class="bodyPad">

    <div class="mainPad">
      <div class="headPad">
        <div class="m-4">
          <div class="text-center text-xl">商家列表</div>
        </div>
      </div>
      <div class="businessList">
        <div class="businessCard" v-for="item in business">
          <div class="businessIcon">
            <img :src=item.img>
          </div>
          <div class="businessInfo" @click="businessInf(item.id)">
            <div>{{ item.businessName }}</div>
            <div>
              <div class="flex text-center gap-6">
                <div class="flex w-35" >
                  <i class="fas fa-star"></i>
                  <i class="fas fa-star"></i>
                  <i class="fas fa-star"></i>
                  <i class="fas fa-star"></i>
                  <i class="fas fa-star"></i>
                  {{ item.remarks }}
                </div>
                <div class="flex-none">
                  <p class="text-white bg-sky-500 ">蜂鸟快送</p>
                </div>
              </div>
              <div class="grid grid-cols-2 gap-4 text-center">
                <div>￥{{ item.starPrice }}起送|￥{{ item.deliveryPrice }}配送</div>
                <div>{{ item.address }}</div>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>

    <div class="barPad">
      <div>
        <router-link to="/">
          <i class="far fa-home"></i>
          <br/>首页
        </router-link>
      </div>
      <div>
        <router-link to="/">
          <i class="fas fa-compass"></i>
          <br/>发现
        </router-link>
      </div>
      <div>
        <router-link to="/order">
          <i class="fas fa-receipt"></i>
          <br/>订单
        </router-link>
      </div>
      <div>
        <router-link to="/login">
          <i class="fas fa-user"></i>
          <br/>我的
        </router-link>
      </div>
    </div>

  </div>
</template>

<script setup>
//加载推荐商家列表
import {onMounted, ref} from "vue";
import axios from "axios";
import cacheService from "../provider/cacheService";
import {useRouter} from "vue-router";
const router = useRouter()

//获取商家食物列表
const businessInf = (val) => {
  console.log(val)
  console.log(cacheService.add('businessId',val))//缓存商家id
  router.push('/businessInf')
}
//加载点餐分类商家列表
const business = ref([])
const getBusinessList = () => {
  axios.get('/business/findbyordertypeid?ordertypeid='+cacheService.get('type'),{
        headers: {'Authorization': "Bearer " + cacheService.get('token')}
      }
  )
      .then(function (res) {
        console.log(res.data)
        business.value = res.data
      })
}
//初始化数据函数
onMounted(async () => {
  await getBusinessList()
})
</script>

<style scoped>
@import "../style/css/businessList.css";
@import "https://pro.fontawesome.com/releases/v5.10.0/css/all.css";
</style>