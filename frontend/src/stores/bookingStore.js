import { defineStore } from 'pinia'

export const useBookingStore = defineStore('booking', {
    state: () => ({
        latestBooking: null
    }),
    actions: {
        setLatestBooking(bookingData) {
            this.latestBooking = bookingData
        },
        clearBooking() {
            this.latestBooking = null
        }
    }
})