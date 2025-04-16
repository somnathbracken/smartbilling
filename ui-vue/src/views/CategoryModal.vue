<template>
    <div v-if="show" class="fixed inset-0 bg-black bg-opacity-30 flex justify-center items-center z-50">
      <div class="bg-white p-6 rounded shadow-md w-full max-w-md">
        <h2 class="text-xl font-bold mb-4">Add New Category</h2>
        <input v-model="form.category_code" placeholder="Category Code" class="input" />
        <input v-model="form.category_name" placeholder="Category Name" class="input mt-2" />
        <div class="flex justify-end mt-4 space-x-2">
          <button class="btn-secondary" @click="$emit('close')">Cancel</button>
          <button class="btn-primary" @click="submit">Save</button>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { reactive, defineProps, defineEmits } from 'vue'
  
  const props = defineProps({ show: Boolean })
  const emit = defineEmits(['close', 'save'])
  
  const form = reactive({
    category_code: '',
    category_name: ''
  })
  
  function submit() {
    if (!form.category_name) return
    emit('save', { ...form })
    emit('close')
  }
  </script>
  