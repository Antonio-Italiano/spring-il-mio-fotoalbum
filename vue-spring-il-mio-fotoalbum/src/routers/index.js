import { createRouter,createWebHistory} from "vue-router";

import HomePage from '../pages/HomePage.vue';
import NotFoundPage from '../pages/NotFoundPage.vue';
import FotoDetails from '../pages/FotoDetails.vue';
import Contacts from '../pages/Contacts.vue';

const router = createRouter({
    history: createWebHistory(),
    linkExactActiveClass: 'active',
    linkActiveClass: 'active',
    routes: [
        { path: '/', name: 'home', component: HomePage },
        { path: '/contacts', name: 'contacts', component: Contacts },
        { path: '/foto/:id', name: 'foto-details', component: FotoDetails },
        { path: '/:pathMatch(.*)*', component: NotFoundPage },
    ]
})

export { router };