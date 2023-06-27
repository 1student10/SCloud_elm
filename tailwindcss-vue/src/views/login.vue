<template>
  <div class="bodyPad">

    <div class="mainPad">
      <div class="headPad">
        <div class="m-2 text-white">
          <div class="text-center text-xl" @click="loginTest()">用户登录</div>
        </div>
      </div>
      <form>
        <div class="flex text-center gap-0 my-3">

          <div class="flex w-18 text-base text-center" >
            用户名：
            <!--                <i class="fal fa-times" style="color:black">&nbsp;</i>-->
            <!--                <i class="fas fa-minus-circle" style="color:grey"></i>&nbsp;&nbsp;1&nbsp;&nbsp;-->
            <!--                <i class="fas fa-plus-circle" style="color:dodgerblue"></i>-->
          </div>
          <div class="flex-none w-3/4 text-right">
            <input class="w-full" type="phonenum" placeholder="用户名" v-model="username">
          </div>
        </div>
        <div class="flex text-center gap-0 my-3">
          <div class="flex w-18 text-base text-center" >
            密码：&nbsp;&nbsp;&nbsp;
            <!--                <i class="fal fa-times" style="color:black">&nbsp;</i>-->
            <!--                <i class="fas fa-minus-circle" style="color:grey"></i>&nbsp;&nbsp;1&nbsp;&nbsp;-->
            <!--                <i class="fas fa-plus-circle" style="color:dodgerblue"></i>-->
          </div>
          <div class="flex-none w-3/4 text-right">
            <input class="w-full" type="password" placeholder="密码：" v-model="password">
          </div>
        </div>


        <span ></span>
        <div class="buttonPad m-2 flex flex-row">
          <a class="btn btn-full btn-green  text-xl" @click="login()">
            <i class="fas fa-sign-in-alt"></i>
            登录
          </a>
          <!--            <a class="btn btn-full btn-blue">-->
          <!--                <i class="fas fa-sign-out-alt"></i>-->
          <!--                退出-->
          <!--            </a>-->
        </div>
      </form>
      <div class="buttonPad m-2 flex flex-row" @click="register()">
        <a class="btn btn-full btn-gray  text-xl">
          <!--                <i class="fas fa-sign-in-alt"></i>-->
          去注册
        </a>
        <!--            <a class="btn btn-full btn-blue">-->
        <!--                <i class="fas fa-sign-out-alt"></i>-->
        <!--                退出-->
        <!--            </a>-->
      </div>


    </div>
<!--    <div @click="getFood()">getFood</div>-->
<!--    <div @click="getBusinessList()">getBusinessList</div>-->
<!--    <div @click="clear()">退出登录</div>-->

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
        <router-link to="/login" @click="clear()">
          <i class="fas fa-user"></i>
          <br/>我的
        </router-link>
      </div>
    </div>

  </div>
</template>

<script setup>
import {useRouter} from "vue-router";
import axios from "axios";
import {ref} from "vue"
import cacheService from "../provider/cacheService";

//页面跳转
const router = useRouter()
const register = () => {
  router.push('/register')
}
//登录
const username = ref('')
const password = ref('')
const login = () => {
  const data = {
    "username": username.value,
    "password": password.value,
    "tokenType": "info",
    "grant_type": "password",
    "client_id": "oauth_isolationArea",
    "Access-Control-Allow-Origin": "*"
  }
  console.log(data.username)
  console.log(data.password)
  axios.post('/pub/auth/auth-token', data, {
    headers: {'Content-Type': 'application/json'}
      }
  )
      .then(function (res) {
        console.log(res.data)
        localStorage.token = res.data.accessToken
        console.log(localStorage.token)
        if(localStorage.token != null){
          cacheService.add('userid',username.value)
          router.push('/')
        }
        else {
          alert("用户名或密码错误")
        }
      })
}
//登录测试，不需要token
const loginTest = () => {
  const data = {
    "id": "xie",
    "password": "{noop}123",
  }
  console.log(data.id)
  console.log(data.password)
  axios.post('/user/login', data, {
    headers: {'Content-Type': 'multipart/form-data'}
      }
  )
      .then(function (res) {
        console.log(res.data)
        if(res.data != null){
          alert("登录成功")
        }
        else {
          alert("用户名或密码错误")
        }
      })
}
//获取食物测试，需要token
const getFood = () =>{
  axios.get('/food/findall',{
    headers: {"Authorization": "Bearer " + localStorage.getItem('token')}
      }
  )
      .then(function (res){
        console.log(res.data)
        alert("获取食物列表成功")
      })
}
const list = ref([])
const getBusinessList = () => {
  axios.get('/business/findall',{
        headers: {"Authorization": "Bearer " + localStorage.getItem('token')}
      }
  )
      .then(function (res) {
        console.log(res.data)
        list.value = res.data
      })
}

//退出登录，清除token
const clear = () => {
  cacheService.clear();
}
</script>

<style scoped>
@import "../style/css/login.css";
@import "https://pro.fontawesome.com/releases/v5.10.0/css/all.css";
</style>