const routes = [
  {path: "/", component: MainView},
  {path: "/tables", component: TablesView},
  {path: "/data_rest", component: DataRestView},
];

const router = new VueRouter({
  routes
});

var app = new Vue({
  router,
  data: {
    current: "main"
  }
}).$mount("#app");
