Vue.component('app-header', {
  template: '<div class="header">' +
    '<router-link to=\"/tables\">Tables</router-link>'
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
