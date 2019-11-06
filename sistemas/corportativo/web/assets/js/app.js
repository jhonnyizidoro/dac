const ENDPOINT = 'http://localhost:8080/corportativo_war_exploded/api/'

document.addEventListener('DOMContentLoaded', () => {

    const stateSelect = document.querySelector('select#state')
    const citySelect = document.querySelector('select#city')
    if (stateSelect) {
        stateSelect.addEventListener('change', () => {
            fetch(`${ENDPOINT}city/state/${stateSelect.value}`)
                .then(res => res.json()).then(cities => {
                    citySelect.innerHTML = ''
                    cities.forEach(city => {
                        const cityOption = document.createElement('option')
                        cityOption.value = city.id
                        cityOption.innerText = city.name
                        citySelect.append(cityOption)
                    })
                })
        })
    }

    document.querySelectorAll('[data-mask]').forEach(input => IMask(input, { mask: input.dataset.mask }))

})