const swiper = new Swiper('.swiper' , {
    // Optional parameters
    spaceBetween: 30,
    centeredSlides: true,
    loop: true,
    autoplay: {
        delay: 2500,
        disableOnInteraction: false,
    },
    breakpoints: {
        320: {
            slidesPerView: 1, //브라우저가 320보다 클 때
            spaceBetween: 12,
        },
        768: {
            slidesPerView: 1, //브라우저가 768보다 클 때
            spaceBetween: 20,
        },
    }
});