<template>
    <tr>
      <td>
        <input
          type="text"
          v-model="item.product"
          placeholder="Product name"
          class="input-sm"
        />
      </td>
      <td>
        <input
          type="number"
          v-model.number="item.qty"
          min="1"
          class="input-sm w-20"
        />
      </td>
      <td>
        <input
          type="number"
          v-model.number="item.price"
          step="0.01"
          class="input-sm w-24"
        />
      </td>
      <td>
        <input
          type="number"
          v-model.number="item.gst"
          step="0.01"
          class="input-sm w-16"
        />
      </td>
      <td>
        ₹{{ itemTotal.toFixed(2) }}
      </td>
      <td>
        <button @click="$emit('remove')" class="text-red-500 hover:text-red-700 text-sm">🗑 Remove</button>
      </td>
    </tr>
  </template>
  
  <script setup>
  import { computed } from 'vue'
  const props = defineProps({
    item: {
      type: Object,
      required: true
    }
  })
  
  const itemTotal = computed(() => {
    const price = props.item.price || 0
    const qty = props.item.qty || 0
    const gst = props.item.gst || 0
    const total = price * qty
    return total + (total * gst / 100)
  })
  </script>
  
  <style scoped>
  .input-sm {
    @apply border border-gray-300 p-1 rounded text-sm;
  }
  </style>
  