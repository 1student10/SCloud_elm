<script setup lang="ts">
import { ref } from 'vue'
import  '../style/index.scss'

defineProps<{ msg: string }>()

const count = ref(0)
</script>

<template>

  <h1>{{ msg }}</h1>

  <div class="card">
    <button type="button" @click="count++">count is {{ count }}</button>
    <p>
      Edit
      <code>components/HelloWorld.vue</code> to test HMR
    </p>
  </div>

  <!--card-->
  <div class="card">
    <div class="flex items-center space-x-4">
      <img src="/vite.svg" alt="" width="88" height="88" class="flex-none rounded-lg bg-slate-100" loading="lazy" />
      <div class="min-w-0 flex-auto space-y-1 font-semibold">
        <p class="text-cyan-500 dark:text-cyan-400 text-sm leading-6">
          <abbr title="Episode">Ep.</abbr> 128
        </p>
        <h2 class="text-slate-500 dark:text-slate-400 text-sm leading-6 truncate">
          Scaling CSS at Heroku with Utility Classes
        </h2>
        <p class="text-slate-900 dark:text-slate-50 text-lg">
          Full Stack Radio
        </p>
      </div>
    </div>

    <div class="space-y-2">
      <div class="relative">
        <div class="bg-slate-100 dark:bg-slate-700 rounded-full overflow-hidden">
          <div class="bg-cyan-500 dark:bg-cyan-400 w-1/2 h-2" role="progressbar" aria-label="music progress" aria-valuenow="1456" aria-valuemin="0" aria-valuemax="4550"></div>
        </div>
        <div class="ring-cyan-500 dark:ring-cyan-400 ring-2 absolute left-1/2 top-1/2 w-4 h-4 -mt-2 -ml-2 flex items-center justify-center bg-white rounded-full shadow">
          <div class="w-1.5 h-1.5 bg-cyan-500 dark:bg-cyan-400 rounded-full ring-1 ring-inset ring-slate-900/5"></div>
        </div>
      </div>
      <div class="flex justify-between text-sm leading-6 font-medium tabular-nums">
        <div class="text-cyan-500 dark:text-slate-100">24:16</div>
        <div class="text-slate-500 dark:text-slate-400">75:50</div>
      </div>
    </div>
  </div>

</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
