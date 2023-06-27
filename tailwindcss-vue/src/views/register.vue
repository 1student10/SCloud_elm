<template>
  <div class="bodyPad">

    <div class="mainPad">
      <div class="headPad">
        <div class="m-2 text-white">
          <div class="text-center text-xl">用户注册</div>
        </div>
      </div>
      <form>
        <div class="flex text-center gap-0 my-3">

          <div class="flex w-18 text-base text-center" >
            手机号码：
            <!--                <i class="fal fa-times" style="color:black">&nbsp;</i>-->
            <!--                <i class="fas fa-minus-circle" style="color:grey"></i>&nbsp;&nbsp;1&nbsp;&nbsp;-->
            <!--                <i class="fas fa-plus-circle" style="color:dodgerblue"></i>-->
          </div>
          <div class="flex-none w-3/4 text-right">
            <input class="w-full" type="phonenum" placeholder="手机号码">
          </div>
        </div>
        <div class="flex text-center gap-0 my-3">
          <div class="flex w-18 text-base text-center" >
            &nbsp;&nbsp;&nbsp;密码：&nbsp;&nbsp;&nbsp;&nbsp;
            <!--                <i class="fal fa-times" style="color:black">&nbsp;</i>-->
            <!--                <i class="fas fa-minus-circle" style="color:grey"></i>&nbsp;&nbsp;1&nbsp;&nbsp;-->
            <!--                <i class="fas fa-plus-circle" style="color:dodgerblue"></i>-->
          </div>
          <div class="flex-none w-3/4 text-right">
            <input class="w-full" type="password" placeholder="密码：" v-model="password">
          </div>
        </div>
        <div class="flex text-center gap-0 my-3">

          <div class="flex w-18 text-base text-center" >
            确认密码：
            <!--                <i class="fal fa-times" style="color:black">&nbsp;</i>-->
            <!--                <i class="fas fa-minus-circle" style="color:grey"></i>&nbsp;&nbsp;1&nbsp;&nbsp;-->
            <!--                <i class="fas fa-plus-circle" style="color:dodgerblue"></i>-->
          </div>
          <div class="flex-none w-3/4 text-right">
            <input class="w-full" type="phonenum" placeholder="确认密码">
          </div>
        </div>
        <div class="flex text-center gap-0 my-3">

          <div class="flex w-18 text-base text-center" >
            用户姓名：
            <!--                <i class="fal fa-times" style="color:black">&nbsp;</i>-->
            <!--                <i class="fas fa-minus-circle" style="color:grey"></i>&nbsp;&nbsp;1&nbsp;&nbsp;-->
            <!--                <i class="fas fa-plus-circle" style="color:dodgerblue"></i>-->
          </div>
          <div class="flex-none w-3/4 text-right">
            <input class="w-full" type="phonenum" placeholder="用户姓名" v-model="username">
          </div>
        </div>
        <div class="flex text-center gap-0 my-3">

          <div class="flex w-18 text-base text-center" >
            &nbsp;&nbsp;&nbsp;性别：&nbsp;&nbsp;&nbsp;&nbsp;
            <!--                <i class="fal fa-times" style="color:black">&nbsp;</i>-->
            <!--                <i class="fas fa-minus-circle" style="color:grey"></i>&nbsp;&nbsp;1&nbsp;&nbsp;-->
            <!--                <i class="fas fa-plus-circle" style="color:dodgerblue"></i>-->
          </div>
          <div class="flex-none w-3/4 text-left">
            <input type="radio" name="boy" checked="checked">男
            <input type="radio" name="girl">女
          </div>
        </div>


        <span ></span>
        <div class="buttonPad m-2 flex flex-row" @click="register()">
          <a class="btn btn-full btn-green  text-xl">
            <i class="fas fa-sign-in-alt"></i>
            注册
          </a>
          <!--            <a class="btn btn-full btn-blue">-->
          <!--                <i class="fas fa-sign-out-alt"></i>-->
          <!--                退出-->
          <!--            </a>-->
        </div>
      </form>
      <!--        <div class="buttonPad m-2 flex flex-row" onclick="location.href='./register.html'">-->
      <!--            <a class="btn btn-full btn-gray  text-xl">-->
      <!--                &lt;!&ndash;                <i class="fas fa-sign-in-alt"></i>&ndash;&gt;-->
      <!--                去注册-->
      <!--            </a>-->
      <!--            &lt;!&ndash;            <a class="btn btn-full btn-blue">&ndash;&gt;-->
      <!--            &lt;!&ndash;                <i class="fas fa-sign-out-alt"></i>&ndash;&gt;-->
      <!--            &lt;!&ndash;                退出&ndash;&gt;-->
      <!--            &lt;!&ndash;            </a>&ndash;&gt;-->
      <!--        </div>-->


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
//登录
import {ref} from "vue";
import axios from "axios";
import cacheService from "../provider/cacheService";
import {useRouter} from "vue-router";

//注册
const router = useRouter()
const username = ref('')
const password = ref('')
const register = () => {
  const data = {
    "createdDate": "2023-01-06T05:16:38.653Z",
    "lastModifiedDate": "2023-01-06T05:16:38.653Z",
    "id": username.value,
    "password": username.value,
    "username": password.value,
    "sex": 0,
    "img": "string",
    "delTag": 0,
    "enabled": true,
    "credentialsNonExpired": true,
    "accountNonLocked": true,
    "accountNonExpired": true,
    "credentialsExpiredDate": "2023-01-08T05:16:38.653Z",
    "accountExpiredDate": "2023-01-08T05:16:38.653Z",
    "accessToken": "string",
    "entityId": {}
  }
  axios.post('/user', data, {
        headers: {'Content-Type': 'application/json'}
      }
  )
      .then(function (res) {
        console.log(res.data)
        if(res.data != null){
          cacheService.add('userid',username.value)
          router.push('/login')
        }
        else {
          alert("用户名已存在")
        }
      })
}
</script>

<style scoped>
@import "../style/css/register.css";
@import "https://pro.fontawesome.com/releases/v5.10.0/css/all.css";
</style>