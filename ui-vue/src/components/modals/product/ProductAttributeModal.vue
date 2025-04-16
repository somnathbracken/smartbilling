<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white p-6 rounded-lg shadow-lg w-[28rem]">
      <h2 class="text-lg font-semibold mb-4">Add Product Attribute</h2>
      <form @submit.prevent="saveProductAttribute">
        <div class="mb-4">
          <label class="block mb-1 font-medium">Product ID</label>
          <input v-model="productAttr.product_id" type="number" class="w-full p-2 border rounded" required />
        </div>
        <div class="mb-4">
          <label class="block mb-1 font-medium">Attribute Type</label>
          <select v-model="productAttr.attribute_id" class="w-full p-2 border rounded">
            <option v-for="type in attributeTypes" :key="type.id" :value="type.id">
              {{ type.attribute_name }}
            </option>
          </select>
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
import { reactive, ref, onMounted } from 'vue';

const productAttr = reactive({
  id: null,
  product_id: '',
  attribute_id: ''
});

const attributeTypes = ref([]);

const fetchAttributeTypes = () => {
  // Dummy attribute types
  attributeTypes.value = [
    { id: 1, attribute_name: 'Color' },
    { id: 2, attribute_name: 'Size' }
  ];
};

const saveProductAttribute = () => {
  console.log('Saved Product Attribute:', productAttr);
  // API call logic here
  productAttr.id = null;
  productAttr.product_id = '';
  productAttr.attribute_id = '';
  emit('saved');
  emit('close');
};

onMounted(() => {
  fetchAttributeTypes();
});

defineEmits(['close', 'saved']);
</script>
