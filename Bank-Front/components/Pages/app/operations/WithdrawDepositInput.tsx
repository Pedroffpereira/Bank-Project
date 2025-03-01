import api from "@/Configuration/api";
import { useSession } from "@/app/context/ctx";
import { styles } from "@/components/styles/app/styles";
import { useRouter } from "expo-router";
import { useState } from "react";
import { View, Text, Pressable, TextInput } from "react-native";

export default function WithdrawDepositInput({ url }) {

    const router = useRouter();
    const { session } = useSession();
    const [depositRequest, useDepositRequest] = useState({
        ammount: Number,
    });
    const [error, useError] = useState("");
    function handleAmmount(ammount: String) {
        useDepositRequest({
            ammount: ammount
        })
    }

    return (
        <View>

            <View>
                <Text style={styles.text}>Valor</Text>
                <TextInput style={styles.input} onChangeText={text => handleAmmount(text)} keyboardType='numeric' />
                <Text style={styles.error}>{error}</Text>
            </View>
            <Pressable style={styles.button} onPress={async () => {
                useError("")
                if (depositRequest.ammount == "") {
                    useError("O numero tem que ser preenchido");
                    return
                }
                if (isNaN(depositRequest.ammount)) {
                    useError("O Valor tem de ser um numero");
                    return
                }
                if (depositRequest.ammount < 0) {

                    useError("O numero tem de ser possitivo");
                    return
                }

                try {
                    await api.post(url, depositRequest,
                        {
                            headers: {
                                Authorization: 'Bearer ' + session
                            },
                        }
                    );
                    router.replace("/(app)")
                } catch (errors) {
                    if (errors.status == 'NOT_FOUND') {
                        Alert.alert(
                            'Erro',
                            errors.message
                        )
                        return
                    }
                    useError(errors.response.data.message)
                }


            }}>
                <Text style={styles.button.text}>Enviar</Text>
            </Pressable>
        </View >
    )
}