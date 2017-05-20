var app = new Vue({
  el: '#app',
  data: {
    header: 'This is header',
    rows: [
      {
        'column1': 'R1, C1',
        'column2': 'R1, C2'
      },
      {
        'column1': 'R2, C1',
        'column2': 'R2, C2'
      }
    ],
    showHiddenText: false,
    addColumn1: "",
    addColumn2: "",
    weather: {
      main: "Undefined",
      description: "Underfined"
    }
  },
  methods: {
    toggleHiddenText: function() {
      this.showHiddenText = !this.showHiddenText;
    },

    addRow: function() {
      if (this.addColumn1.trim() == "" || this.addColumn2.trim() == "") {
        alert("Both column must not be blank");
        return;
      }
      this.rows.push({"column1": this.addColumn1, "column2": this.addColumn2});
      this.addColumn1 = "";
      this.addColumn2 = "";
    },

    removeRow: function(index) {
      this.rows.splice(index, 1);
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
})
