const CACHE_NAME = 'webapp_ecompleto_cache'
const CACHING_DURATION = 15 * 60
const BLACK_LIST = /(google)/

self.addEventListener('fetch', event => {
	const {request} = event
	if (request.url.match(BLACK_LIST) || request.method === "POST") {
		return
	}
	event.respondWith(
		caches.open(CACHE_NAME).then(cache => {
			return cache.match(request).then(response => {
				if (response) {
					const expirationDate = Date.parse(response.headers.get('sw-cache-expires'))
					const now = new Date()
					if (expirationDate > now) {
						return response
					}
				}
				return fetch(request.url, {cache: 'no-cache'}).then(liveResponse => {
					const expires = new Date()
					expires.setSeconds(expires.getSeconds() + CACHING_DURATION)
					const cachedResponseFields = {
						status: liveResponse.status,
						statusText: liveResponse.statusText,
						headers: {
							'sw-cache-expires': expires.toUTCString()
						},
					}
					liveResponse.headers.forEach((header, headerName) => {
						cachedResponseFields.headers[headerName] = header
					})
					const returnedResponse = liveResponse.clone()
					return liveResponse.blob().then(body => {
						cache.put(request, new Response(body, cachedResponseFields))
						return returnedResponse
					})
				})
			})
		})
	)
})