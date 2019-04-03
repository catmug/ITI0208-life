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

    it('checks if we can add another green passed test', () => {
        const wrapper = shallowMount(Login, {
        })
        console.log(wrapper.vm.$data.username)
        expect(wrapper.vm.$data.username).toMatch('')
    })


    it('checks testing of headers', () => {
        const wrapper = shallowMount(Login, {
        })
        console.log(wrapper.vm.$data.username)
        expect(wrapper.vm.$data.username).toMatch('')
    })


    it('checks testing of love', () => {
        const wrapper = shallowMount(Login, {
        })
        console.log(wrapper.vm.$data.username)
        expect(wrapper.vm.$data.username).toMatch('')
    })
})
