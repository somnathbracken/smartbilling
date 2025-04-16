<template>
    <div v-if="show" class="modal-overlay">
      <div class="modal-box">
        <h2 class="modal-title">Add New Unit of Measure</h2>
        <input v-model="form.uom_name" placeholder="UOM Name" class="input" />
        <div class="modal-actions">
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
  
  const form = reactive({ uom_name: '' })
  
  function submit() {
    if (!form.uom_name) return
    emit('save', { ...form })
    emit('close')
  }
  </script>
  