Vue.component('app-header', {
  template: '<div class="header">' +
    '<app-head url="index.html" headKey="index" v-bind:current="current">Main</app-head>' +
    '<app-head url="tables.html" headKey="tables" v-bind:current="current">Tables</app-head>' +
    '<app-head url="data_rest.html" headKey="data_rest" v-bind:current="current">Data Rest</app-head>' +
  '</div>',
  props: ['current'],
  components: {
    'app-head': {
      template: '<div class="head" ' +
                  'v-on:click="redirect" ' +
                  'v-bind:class="{active: current==headKey}"><slot></slot></div>',
      props: ['url', 'headKey', 'current'],
      methods: {

        redirect : function() {
          location.href = this.url;
        }

      }
    }
  }
});
