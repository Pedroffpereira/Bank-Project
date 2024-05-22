import React from 'react';
import { useStorageState } from './useStorageState';
import api from '@/Configuration/api';
import { Redirect, useRouter } from 'expo-router';
import alert from '@/components/alert';
const AuthContext = React.createContext<{
    signIn: (loginRequest) => void;
    signOut: () => void;
    signUp: (signUpRequest) => void;
    session?: string | null;
    isLoading: boolean;
}>({
    signIn: (loginRequest) => null,
    signOut: () => null,
    signUp: (signUpRequest) => null,
    session: null,
    isLoading: false,
});

// This hook can be used to access the user info.
export function useSession() {
    const value = React.useContext(AuthContext);
    if (process.env.NODE_ENV !== 'production') {
        if (!value) {
            throw new Error('useSession must be wrapped in a <SessionProvider />');
        }
    }

    return value;
}



export function SessionProvider(props: React.PropsWithChildren) {
    const [[isLoading, session], setSession] = useStorageState('session');

    return (
        <AuthContext.Provider
            value={{
                signIn: async (loginRequest) => {
                    try {
                        const { data } = await api.post("/api/v1/users/login", loginRequest);
                        setSession(data.token);
                        return {
                            status: 'ok'
                        }
                    } catch (error) {
                        return {
                            status: 'ko',
                            messages: error.response.data
                        }
                    }
                },
                signOut: () => {
                    setSession(null);
                },
                signUp: async (signUpRequest) => {
                    try {
                        await api.post("/api/v1/users/registation", signUpRequest);
                        return {
                            status: 'ok'
                        }
                    } catch (error) {
                        if (error == '') {
                            alert('Erro', 'Numero de contrato e password estão erradas');
                        }
                        return {
                            status: 'ko',
                            messages: error.response.data
                        }
                    }

                },
                session,
                isLoading,
            }
            } >
            {props.children}
        </ AuthContext.Provider>
    );
}
