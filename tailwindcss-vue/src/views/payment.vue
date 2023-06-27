<template>
  <div class="bodyPad">

    <div class="mainPad">
      <div class="headPad">
        <div class="m-2 text-white">
          <div class="text-center text-xl">在线支付</div>
        </div>
      </div>

      <div class="orderInf ml-1">
        <!--            <img src="images/饺子店图.jpg">-->
        <div class="antialiased text-lg text-left my-2">订单信息：</div>
        <!--            <span></span>-->
        <!--            <div class="text-xl text-yellow-600">￥15起送|￥3配送</div>-->
        <!--            <div class="text-sm">结合现代健康饮食理念，精选上好食材，为顾客提供真材实料、现包现煮的美味健康水饺。</div>-->
      </div>
      <div class="totalCard">
        <div class="totalInfo">
          <div class="flex text-center gap-x-16">
            <div class="flex w-5/6 ml-1 text-lg" >
              {{ cacheService.get('businessName') }}<i class="fas fa-angle-down my-1"></i>
            </div>
            <div class="flex-none">
              <p class="text-xl m-1 text-red-500 text-right">￥{{ cacheService.get('total') }}</p>
            </div>

          </div>

        </div>
      </div>
      <div class="foodList">
        <div class="foodCard" v-for="item in pay">
          <div class="foodInfo">
            <div>
              <div class="flex text-center gap-24">
                <div class="flex w-35" >
                  {{ item.foodName }}&nbsp;&nbsp;
                  <i class="fal fa-times my-1" style="color:black">&nbsp;</i>
                  <i class="fas fa-minus-circle my-1" style="color:grey"></i>&nbsp;&nbsp;{{ item.quantity }}&nbsp;&nbsp;
                  <i class="fas fa-plus-circle my-1" style="color:dodgerblue"></i>
                </div>
                <div class="flex-none">
                  ￥{{ item.price }}
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="foodCard">
          <div class="foodInfo">
            <div>
              <div class="flex text-center gap-x-64">
                <div class="flex w-35" >
                  配送费&nbsp;
                  <!--                                <i class="fal fa-times" style="color:black">&nbsp;</i>-->
                  <!--                                <i class="fas fa-minus-circle" style="color:grey"></i>&nbsp;&nbsp;1&nbsp;&nbsp;-->
                  <!--                                <i class="fas fa-plus-circle" style="color:dodgerblue"></i>-->
                </div>
                <div class="flex-none">
                  ￥{{ cacheService.get('deliveryPrice') }}
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
      <span ></span>
      <div class="payCard">
        <div class="payInfo">
          <!--                    <div>韭菜饺子(蒸饺）</div>-->
          <div>
            <div class="flex text-center gap-44 m-1">
              <div class="payIcon">
                <img class="m-1" src="images/wechat.png">
              </div>
              <!--                        <div class="flex w-35" >-->
              <!--                            微信支付-->
              <!--                        </div>-->
              <div class="flex-none">
                <p class="text-base m-2"><i class="fas fa-check-circle" style="color:green"></i></p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="payCard">
        <div class="payInfo">
          <!--                    <div>韭菜饺子(蒸饺）</div>-->
          <div>
            <div class="flex text-center gap-44 m-1">
              <div class="payIcon">
                <img class="m-1" src="images/alipay.png">
              </div>
              <!--                        <div class="flex w-35" >-->
              <!--                            微信支付-->
              <!--                        </div>-->
              <div class="flex-none">
                <p class="text-base m-2"><i class="fas fa-check-circle" style="color:grey"></i></p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <span ></span>
      <div class="buttonPad mx-2 flex flex-row">
        <a class="btn btn-full btn-green  text-xl">
          <i class="fas fa-check"></i>
          确认支付
        </a>
        <!--            <a class="btn btn-full btn-blue">-->
        <!--                <i class="fas fa-sign-out-alt"></i>-->
        <!--                退出-->
        <!--            </a>-->
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

<script setup>
import {onMounted, ref} from "vue";
import cacheService from "../provider/cacheService";
import axios from "axios";

//加载订单细节
const pay = ref([])
const getOrderList = () => {
  pay.value = JSON.parse(cacheService.get('pay'))//将字符串转为json对象
  console.log(pay.value)
}
//初始化数据函数
onMounted(async () => {
  await getOrderList()
})
</script>

<style scoped>
@import "../style/css/payment.css";
@import "https://pro.fontawesome.com/releases/v5.10.0/css/all.css";
</style>