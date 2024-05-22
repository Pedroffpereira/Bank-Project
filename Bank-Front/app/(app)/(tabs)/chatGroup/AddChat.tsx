import api from "@/Configuration/api";
import { useSession } from "@/app/context/ctx";
import { useState } from "react";
import { View, TextInput, Text, Pressable, Alert } from "react-native";
import { StyleSheet, Animated } from "react-native";
import { styles } from "@/components/styles/app/styles";
import alert from "@/components/alert";
import { useRouter } from "expo-router";

const style = StyleSheet.create({
    text: {
        color: '#fff'
    },
    title: {
        marginBottom: 20,
        color: '#fff',
        fontSize: 20
    },
    card: {
        marginVertical: 10,
    }
})
export default function AddChatPage() {

    const router = useRouter();
    const { session } = useSession();
    const [addChatGroupRequest, useAddChatGroupRequest] = useState({
        description: '',
        ibans: [''],
    });
    function handleDescription(text: String) {
        useAddChatGroupRequest({
            ...addChatGroupRequest,
            description: text
        });
    }

    function handleIban(text: String) {
        useAddChatGroupRequest({
            ...addChatGroupRequest,
            ibans: [
                text
            ]
        });
    }
    const [error, useError] = useState({
        description: String,
        iban: String,
    })

    return (
        <View style={styles.container}>
            <View style={styles.center}>
                <View >
                    <Text style={styles.text}>Nome Grupo</Text>
                    <TextInput style={styles.input} onChangeText={text => handleDescription(text)} value={addChatGroupRequest.description} />
                    <Text style={styles.error}>{error.description}</Text>
                </View>
                <View>
                    <Text style={styles.text}>Iban</Text>
                    <TextInput style={styles.input} onChangeText={text => handleIban(text)} value={addChatGroupRequest.ibans[0]} />
                    <Text style={styles.error}>{error.iban}</Text>
                </View>

                <Pressable style={styles.button} onPress={async () => {
                    if (addChatGroupRequest.description == '') {
                        useError({
                            description: 'A descrição não pode ser vazio'
                        })
                        return
                    }
                    if (addChatGroupRequest.ibans[0] == '') {
                        useError({
                            iban: 'O iban não pode ser vazio'
                        })
                        return
                    }
                    try {
                        const { data } = await api.post("/api/v1/chatgroups", addChatGroupRequest,
                            {
                                headers: {
                                    Authorization: 'Bearer ' + session
                                },
                            }
                        );
                        router.replace("/(app)/chatGroup/" + data.id)
                    } catch (errors) {
                        console.log(errors)
                        if (errors.response.status == 404) {
                            alert(
                                'Erro',
                                errors.response.data.message
                            )
                            return
                        }
                        useError(error.response.data)
                    }

                }}>
                    <Text style={styles.button.text}>Adicionar Converça</Text>
                </Pressable>
            </View>
        </View>
    )
}