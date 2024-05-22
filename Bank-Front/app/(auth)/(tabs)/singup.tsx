
import api from "@/Configuration/api";
import { useSession } from "@/app/context/ctx";
import { styles, fadeIn, fadeOut } from "@/components/styles/auth/styles";
import { useRouter } from "expo-router";
import { useState } from "react";
import { View, Text, Pressable, Animated, TextInput } from "react-native";

export default function SingUp() {
    const [signUpRequest, useSignUpRequest] = useState({
        name: String,
        email: String,
        password: String
    });

    const [error, useError] = useState({
        email: "",
        name: "",
        password: "",
    })
    function handleNome(text: String) {
        useSignUpRequest({
            ...signUpRequest,
            name: text
        });
    }
    function handleEmail(text: String) {
        useSignUpRequest({
            ...signUpRequest,
            email: text
        });
    }
    function handlePassword(text: String) {
        useSignUpRequest({
            ...signUpRequest,
            password: text
        });
    }


    const { signUp } = useSession();
    const router = useRouter();
    return (
        <View style={styles.container}>
            <Text style={styles.title}>SingUp</Text>

            <View style={styles.box}>
                <View style={styles.inputLable}>
                    <Text style={styles.inputLable.lable}>Nome(completo)</Text>
                    <TextInput onChangeText={text => handleNome(text)} style={styles.input} />
                    {error?.name ? (<Text >{error.name}</Text>) : (<Text />)}
                </View><View style={styles.inputLable}>
                    <Text style={styles.inputLable.lable}>Email</Text>
                    <TextInput onChangeText={text => handleEmail(text)} style={styles.input} />
                    {error?.email ? (<Text >{error.email}</Text>) : (<Text />)}

                </View>
                <View style={styles.inputLable}>
                    <Text style={styles.inputLable.lable}>Password</Text>
                    <TextInput onChangeText={text => handlePassword(text)} secureTextEntry={true} style={styles.input} />
                    {error?.password ? (<Text >{error.password}</Text>) : (<Text />)}
                </View>
                <View style={styles.buttonDiv}>
                    <Pressable
                        style={styles.button} onPressIn={fadeIn} onPressOut={fadeOut} style={styles.button} onPress={async () => {
                            const response = await signUp(signUpRequest);
                            if (response.status != 'ok') {
                                useError(response.messages)
                                return;
                            }
                            router.replace("/")
                        }} >
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
