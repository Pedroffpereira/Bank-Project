
import { useSession } from "@/app/context/ctx";
import { useRouter } from "expo-router";
import { useState } from "react";
import { View, Text, Pressable, TextInput, StyleSheet, Animated } from "react-native";
import { styles, fadeIn, fadeOut } from "@/components/styles/auth/styles";


export default function Login() {

    const [loginRequest, useLoginRequest] = useState({
        contract: String,
        password: String
    });
    function handleContract(text: String) {
        useLoginRequest({
            ...loginRequest,
            contract: text
        });
    }

    function handlePassword(text: String) {
        useLoginRequest({
            ...loginRequest,
            password: text
        });
    }
    
    const { signIn } = useSession();
    const router = useRouter();
    return (
        <View style={styles.container}>
            <Text style={styles.title}>SingIn</Text>
            <View style={styles.box}>
                <View style={styles.inputLable}>
                    <Text style={styles.inputLable.lable}>Numero de Contrato</Text>
                    <TextInput style={styles.input} onChangeText={text => handleContract(text)} />
                </View>
                <View style={styles.inputLable}>
                    <Text style={styles.inputLable.lable}>Password</Text>
                    <TextInput secureTextEntry={true} style={styles.input} onChangeText={text => handlePassword(text)} />
                </View>
                <View style={styles.buttonDiv}>
                    <Pressable
                        style={styles.button} onPressIn={fadeIn} onPressOut={fadeOut} style={styles.button} onPress={() => {
                            signIn(loginRequest);
                            router.replace("/(app)")
                        }}>
                        <Animated.View
                        >
                            <Text style={styles.button.text}>
                                Login
                            </Text>
                        </Animated.View>
                    </Pressable>
                </View>
            </View>
        </View>
    );
}

