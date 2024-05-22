import api from "@/Configuration/api";
import { useSession } from "@/app/context/ctx";
import { styles } from "@/components/styles/app/styles";
import { useState } from "react";
import { View, Pressable, Text, TextInput, Alert } from "react-native";

export default function TransferPage() {

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
                        try {
                            await api.post("/api/v1/operations/transfer", transferRequest,
                                {
                                    headers: {
                                        Authorization: 'Bearer ' + session
                                    },
                                }
                            );
                        } catch (error) {
                            if (error.status == 'NOT_FOUND') {
                                Alert.alert(
                                    'Erro',
                                    error.message
                                )
                                return
                            }
                            useErrorInput({
                                iban: error.response.data.iban,
                                ...errorInput
                            }
                            )
                            useErrorInput({
                                ammount: error.response.data.ammount,
                                ...errorInput
                            })
                        }

                    }}>
                        <Text style={styles.button.text}>Enviar</Text>
                    </Pressable>
                </View>
            </View>
        </View>
    )
}