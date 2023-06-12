<script>
export default {
    props: {
        foto: Object,
        show: Boolean
    },
    data() {
        return {

        }
    },

}

</script>

<template>
    <div key="id" class="m-4">
        <div class="card" style="width: 18rem;">
            <img :src="foto.url" class="card-img-top" :alt="foto.titolo">
            <div class="card-body">
                <h5 class="card-title">{{ foto.titolo }}</h5>
                <p class="card-text">{{ foto.descrizione }}</p>
                <router-link :to="{ name: 'foto-details', params: { id: foto.id } }" class="btn btn-success me-2"
                    v-if="show">show</router-link>
            </div>
        </div>
    </div>
</template>

<style scoped lang="scss"></style>