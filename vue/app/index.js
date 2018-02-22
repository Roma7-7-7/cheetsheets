const routes = [
  {path: "/", component: MainView, meta: {header: 'main'}},
  {path: "/tables", component: TablesView, meta: {header: 'tables'}},
  {path: "/data_rest", component: DataRestView, meta: {header: 'data_rest'}},
];

const router = new VueRouter({
  routes
});

var app = new Vue({
  router,
  data: {
    current: "main"
  },
  watch: {
    '$route' (to, from) {
      this.current = to.meta.header;
    }
  },

  mounted: function() {
    this.current = this.$route.meta.header;
  }
}).$mount("#app");
