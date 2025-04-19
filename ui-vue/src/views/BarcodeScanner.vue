<template>
    <div class="border p-4 rounded shadow-md w-full max-w-md mx-auto">
      <div v-if="!scanned">
        <div id="reader" class="w-full"></div>
      </div>
  
      <div v-else class="text-center space-y-3">
        <p class="text-green-600 font-semibold">✅ Scan Successful!</p>
        <p class="text-sm text-gray-700">Scanned Code: <strong>{{ lastScanned }}</strong></p>
        <button @click="restartScanner" class="bg-blue-500 text-white px-4 py-2 rounded">Scan Again</button>
      </div>
  
      <button @click="stopScanner" class="mt-4 bg-red-500 text-white px-3 py-1 rounded text-sm block mx-auto">Close Scanner</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, onBeforeUnmount } from 'vue'
  import { Html5Qrcode } from 'html5-qrcode'
  
  const emit = defineEmits(['onScan'])
  let html5QrCode
  
  const scanned = ref(false)
  const lastScanned = ref('')
  
  const startScanner = async () => {
    const config = { fps: 10, qrbox: { width: 250, height: 250 } }
  
    html5QrCode = new Html5Qrcode("reader")
    await html5QrCode.start(
      { facingMode: "environment" },
      config,
      (decodedText) => {
        lastScanned.value = decodedText
        emit('onScan', decodedText)
        scanned.value = true
        stopScanner(false) // pause only
      },
      (errorMessage) => {
        // silent fail
      }
    )
  }
  
  const stopScanner = async (clear = true) => {
    if (html5QrCode) {
      await html5QrCode.stop()
      if (clear) {
        await html5QrCode.clear()
      }
    }
  }
  
  const restartScanner = async () => {
    scanned.value = false
    await startScanner()
  }
  
  onMounted(startScanner)
  onBeforeUnmount(() => stopScanner(true))
  </script>
  