var app = new Vue({
    el: '#lines-app',
    data: {
        lines: [],
        newLineName: null
    },
    mounted() {
        this.refreshTable();
    },
    methods: {
        refreshTable: function() {
            axios.get('/lines').then( value => {
                this.lines = value.data;
            })
        },
        addLine: function () {
            axios.post('/lines', this.newLineName, {
                headers: { 'Content-Type': 'text/plain' }
            }).
            then(response => {
                console.log(response)
            }).
            catch(error => {
                if (error.response.status === 500) {
                    console.log('Error!!!');
                    alert('Error!!!')
                }
            }).finally(() => this.refreshTable());
        },
        removeAll: function() {
            axios.delete('/lines');
            this.refreshTable();
        }
    }
});