const DataRestView = {
  template: "#dataRestView",
  data: function() {
    return {
      weather: {
        main: "Undefined",
        description: "Underfined"
      }
    }
  },

  mounted: function() {
    this.$http.get('http://demo4778058.mockable.io/weather.json').then(response => {
      this.weather = response.body;
    }, response => {
        alert("Failed to load weather");
        console.log(response);
    })
  }
};
