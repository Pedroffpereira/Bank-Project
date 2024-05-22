import api from "@/Configuration/api";
import { useSession } from "@/app/context/ctx";
import alert from "@/components/alert";
import { styles } from "@/components/styles/app/styles";
import { useRouter } from "expo-router";
import { useState } from "react";
import { View, Pressable, Text, TextInput, Alert } from "react-native";

export default function TransferPage() {

    const router = useRouter();
    const { session } = useSession();
    const [errorInput, useErrorInput] = useState({
        iban: '',
        ammount: '',
    });
    const [transferRequest, useTransferRequest] = useState({
        iban: '',
        ammount: '',
    });


    function handleAmmount(ammount: String) {
        useTransferRequest({
            ...transferRequest,
            ammount: ammount
        })
    }
    function handleIban(iban: String) {
        useTransferRequest({
            ...transferRequest,
            iban: iban
        })
    }
    return (
        <View style={styles.container}>
            <Text style={styles.title}>Transferência</Text>
            <View style={styles.center}>
                <View style={styles.space}>
                    <Text style={styles.text}>Iban</Text>
                    <TextInput style={styles.input} onChangeText={text => handleIban(text)} value={transferRequest.iban} />
                    <Text style={styles.error}>{errorInput.iban}</Text>
                </View>
                <View style={styles.space}>
                    <Text style={styles.text}>Amount</Text>
                    <TextInput style={styles.input} onChangeText={text => handleAmmount(text)} keyboardType='numeric' value={transferRequest.ammount} />
                    <Text style={styles.error}>{errorInput.ammount}</Text>
                </View>
                <View style={styles.button}>
                    <Pressable onPress={async () => {
                        if (transferRequest.iban == '') {
                            useErrorInput({ iban: "O Iban tem de ser preenchido" });
                            return
                        }
                        if (isNaN(transferRequest.ammount)) {
                            useErrorInput({ ammount: "O Valor tem de ser um numero" });
                            return
                        }
                        try {
                            await api.post("/api/v1/operations/transfer", transferRequest,
                                {
                                    headers: {
                                        Authorization: 'Bearer ' + session
                                    },
                                }
                            );

                            router.replace("/(app)")
                        } catch (error) {
                            console.log(error)
                            if (error.response.status == '404') {
                                alert(
                                    'Erro',
                                    error.response.data.message
                                )
                                return
                            }
                            useErrorInput(
                                error.response.data
                            )
                        }

                    }}>
                        <Text style={styles.button.text}>Enviar</Text>
                    </Pressable>
                </View>
            </View>
        </View>
    )
}