<!-- src/components/DropdownWithModal.vue -->
<template>
    <div class="mb-4">
      <label class="block font-semibold mb-1">{{ label }}</label>
      <div class="flex items-center space-x-2">
        <select v-model="selectedValue" class="border p-2 rounded w-64">
          <option disabled value="">-- Select --</option>
          <option v-for="option in options" :key="option.id" :value="option.id">
            {{ option[labelKey] }}
          </option>
        </select>
        <button @click="showModal = true" class="bg-blue-500 text-white px-2 py-1 rounded">
          + Add
        </button>
      </div>
  
      <component
        :is="modalComponent"
        v-if="showModal"
        @close="showModal = false"
        @saved="handleSaved"
      />
    </div>
  </template>
  
  <script setup>
  import { ref, watch, defineProps, defineEmits } from 'vue';
  
  const props = defineProps({
    label: String,
    modelValue: [String, Number],
    options: Array,
    labelKey: {
      type: String,
      default: 'name'
    },
    modalComponent: Object
  });
  
  const emit = defineEmits(['update:modelValue', 'refresh']);
  
  const selectedValue = ref(props.modelValue);
  const showModal = ref(false);
  
  watch(() => props.modelValue, (newVal) => {
    selectedValue.value = newVal;
  });
  
  watch(selectedValue, (newVal) => {
    emit('update:modelValue', newVal);
  });
  
  const handleSaved = () => {
    emit('refresh');
    showModal.value = false;
  };
  </script>
  