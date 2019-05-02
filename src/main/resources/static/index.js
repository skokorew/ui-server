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
            });
            this.newLineName = '';
        },
        addLine: function () {
            axios.post('/lines', this.newLineName, {
                headers: { 'Content-Type': 'text/plain' }
            }).
            catch(error => {
                console.log(error.response);
                this.$bvModal.msgBoxOk('Cannot enter existing string: ' + this.newLineName)
            }).finally(() => this.refreshTable());
        },
        removeAll: function() {
            axios.delete('/lines');
            this.refreshTable();
        }
    }
});