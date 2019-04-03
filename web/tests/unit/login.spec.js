import { shallowMount } from '@vue/test-utils'
import Login from '@/components/Login.vue'

describe('Login.vue', () => {
    it('checks if page has values', () => {
        const wrapper = shallowMount(Login, {
            // propsData: { msg }
        })
        const msg = 'Login';
        expect(wrapper.find('h1').text()).toMatch(msg)
    })

    it('checks if username has empty values', () => {
        const wrapper = shallowMount(Login, {
        })
        console.log(wrapper.vm.$data.username)
        expect(wrapper.vm.$data.username).toMatch('')
    })
})
