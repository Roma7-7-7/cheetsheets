var app = new Vue({
  el: "#app",
  data: {
    header: 'This is header',
    showHiddenText: false
  },
  methods: {

    toggleHiddenText: function() {
      this.showHiddenText = !this.showHiddenText;
    }

  }
})
