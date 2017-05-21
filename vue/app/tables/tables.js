const TablesView = {
  template: "#tablesView",
  data: function() {
    return {
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
      addColumn1: "",
      addColumn2: ""
    }
  },
  methods: {

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
  }
};
