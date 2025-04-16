<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white p-6 rounded-lg shadow-lg w-96">
      <h2 class="text-lg font-semibold mb-4">Add Attribute Type</h2>
      <form @submit.prevent="saveAttributeType">
        <div class="mb-4">
          <label class="block mb-1 font-medium">Attribute Name</label>
          <input v-model="attributeType.attribute_name" type="text" class="w-full p-2 border rounded" required />
        </div>
        <div class="flex justify-end space-x-2">
          <button type="button" @click="$emit('close')" class="px-4 py-2 bg-gray-300 rounded">Cancel</button>
          <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded">Save</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue';

const attributeType = reactive({
  id: null,
  attribute_name: ''
});

const saveAttributeType = () => {
  console.log('Saved Attribute Type:', attributeType);
  // API call logic here
  // emit to refresh parent dropdown
  // reset and close
  attributeType.id = null;
  attributeType.attribute_name = '';
  emit('saved');
  emit('close');
};
defineEmits(['close', 'saved']);
</script>
