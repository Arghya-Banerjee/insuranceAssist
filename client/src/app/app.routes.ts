import { Routes } from '@angular/router';
import { Auth } from './pages/auth/auth';
import { Login } from './pages/auth/login/login';
import { Signup } from './pages/auth/signup/signup';
import { Layout } from './pages/layout/layout';
import { Dashboard } from './pages/dashboard/dashboard';
import { AgentDashboard } from './pages/dashboard/agent/agent-dashboard';
import { ClientDashboard } from './pages/dashboard/client/client-dashboard';
import { ClientRegisterPolicy } from './pages/auth/signup/client-register-policy/client-register-policy';
import { ClientRegisterDependents } from './pages/auth/signup/client-register-dependents/client-register-dependents';

import { Profile } from './pages/profile/profile';
import { AgentProfile } from './pages/agent-profile/agent-profile';
import { Privacypolicy } from './pages/common/privacypolicy/privacypolicy';
export const routes: Routes = [
    // {
    //     path:'',
    //     redirectTo: 'login',
    //     pathMatch: 'full'
    // },
    // {
    //     path: 'login',
    //     component: Login
    // },
    { 
        path: 'privacy-policy', 
        component: Privacypolicy },
    {
        path:'',
        redirectTo: 'auth',
        pathMatch: 'full'
    },
    {
        path: 'auth',
        component: Auth
    },
    {
        path: 'auth/dependents-nomination',
        component: ClientRegisterDependents
    },
    {
        path: 'auth/signup',
        component: Signup
    },
    {
        path: 'auth/policy-plan',
        component: ClientRegisterPolicy
    },
    {
        path: '',
        component:Layout,
        children: [
            {
                path:'dashboard/client',
                component: ClientDashboard
            },
            {
                path: 'dashboard/agent',
                component: AgentDashboard
            },
            {
                path: 'profile/client',
                component: Profile
            },
            
            {
                path: 'profile/agent',
                component: AgentProfile
            }]    
    }
];
