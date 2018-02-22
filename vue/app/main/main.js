const MainView = {
  template: '#mainView',
  data: function() {
    return {
      current: 'main',
      header: 'This is header',
      showHiddenText: false
    }
  },
  methods: {

    toggleHiddenText: function() {
      this.showHiddenText = !this.showHiddenText;
    }

  }
}
