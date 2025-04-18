<!-- ConfirmationModal.vue -->
<template>
    <div v-if="visible" class="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center">
      <div class="bg-white p-6 rounded shadow-xl w-96 text-center">
        <p class="text-lg font-semibold mb-4">{{ message }}</p>
        <div class="flex justify-center gap-4">
          <button @click="onConfirm" class="btn">Yes</button>
          <button @click="$emit('cancel')" class="btn-red">No</button>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  defineProps(['visible', 'message'])
  const emit = defineEmits(['confirm', 'cancel'])
  
  const onConfirm = () => emit('confirm')
  </script>
  