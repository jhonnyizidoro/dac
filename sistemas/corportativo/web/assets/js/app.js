window.addEventListener('load', () => {
    if (navigator.serviceWorker && !location.href.includes('localhost')) {
        navigator.serviceWorker.register('/sw.js')
    }
})