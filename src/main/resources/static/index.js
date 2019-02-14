var app = new Vue({
    el: '#lines-app',
    data: {
        lines: [],
        newLineName: null
    },
    mounted() {
        axios.get('/lines').then( value => {
            this.lines = value.data;
        })
    },
    methods: {
        addLine: function () {
            axios.post('/lines', this.newLineName, {
                headers: { 'Content-Type': 'text/plain' }
            });
        }
    }
});