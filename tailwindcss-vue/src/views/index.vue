<template>
  <div class="bodyPad">

    <div class="mainPad">
      <div class="headPad">
        <div class="pl-4">
          <i class="fas fa-map-marker-alt"></i>
          呈贡大学城-云南大学
          <i class="fas fa-angle-down"></i>
        </div>
        <div class="m-4">
          <input type="text" placeholder="搜索饿了么商家，商铺名称"/>
        </div>
      </div>
      <div>
        <!--商品分类-->
        <div class="cataMenu">
          <div class="grid grid-cols-5 gap-2  text-center">
            <div @click="businessList(1)">
              <img src="images/美食.png">
              美食
            </div>
            <div @click="businessList(2)">
              <img src="images/早餐.png">
              早餐
            </div>
            <div @click="businessList(3)">
              <img src="images/跑腿代购.png">
              跑腿代购
            </div>
            <div @click="businessList(4)">
              <img src="images/汉堡披萨.png">
              汉堡披萨
            </div>
            <div @click="businessList(5)">
              <img src="images/甜品饮品.png">
              甜品饮品
            </div>
            <div @click="businessList(6)">
              <img src="images/速食简餐.png">
              速食简餐
            </div>
            <div @click="businessList(7)">
              <img src="images/地方小吃.png">
              地方小吃
            </div>
            <div @click="businessList(8)">
              <img src="images/米粉面馆.png">
              米粉面馆
            </div>
            <div @click="businessList(9)">
              <img src="images/包子粥铺.png">
              包子粥铺
            </div>
            <div @click="businessList(10)">
              <img src="images/炸鸡炸串.png">
              炸鸡炸串
            </div>
          </div>
        </div>
        <!--广告-->
        <div class="adPad">
          <a href="#">
            <img src="images/广告.jpg"/>
          </a>
        </div>
        <!--商家推荐列表-->
        <div class="businessPad">
          <div class="titlePad text-xl">
            <!--                    span显示横杠-->
            <span></span>
            推荐商家
            <span></span>
          </div>
          <div class="searchBar">
            <div>综合排序<i class="fas fa-sort-down"></i></div>
            <div>距离最近</div>
            <div>销量最高</div>
            <div>筛选<i class="fas fa-filter"></i></div>
          </div>
          <div class="businessList">
            <div class="businessCard" v-for="item in list.slice(0,3)" @click="businessInf(item.id)">
              <div class="businessIcon">
                <img :src=item.img>
              </div>
              <div class="businessInfo">
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
        <router-link to="/historicalOrders">
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

<script setup lang="ts">
import {useRouter} from "vue-router";
import axios from "axios";
import {onMounted, ref} from "vue";
import cacheService from "../provider/cacheService";
const router = useRouter()
//页面跳转
const businessList = (val: number) => {
  cacheService.add('type',val)//缓存点餐分类
  router.push('/businessList')
}
//获取商家食物列表
const businessInf = (val: number) => {
  console.log(val)
  console.log(cacheService.add('businessId',val))//缓存商家id
  router.push('/businessInf')
}
//加载推荐商家列表
const list = ref([])
const getBusinessList = () => {
  axios.get('/business/findall',{
    headers: {'Authorization': "Bearer " + cacheService.get('token')}
      }
  )
      .then(function (res) {
        console.log(res.data)
        list.value = res.data
      })
}
//初始化数据函数
onMounted(async () => {
  await getBusinessList()
})
</script>

<style scoped>
@import "../style/css/index.css";
@import "https://pro.fontawesome.com/releases/v5.10.0/css/all.css";
</style>