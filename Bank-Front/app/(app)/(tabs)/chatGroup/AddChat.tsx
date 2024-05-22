import api from "@/Configuration/api";
import { useSession } from "@/app/context/ctx";
import { useState } from "react";
import { View, TextInput, Text, Pressable } from "react-native";
import { StyleSheet, Animated } from "react-native";
import { styles } from "@/components/styles/app/styles";

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
    const [error, userError] = useState({
        description: String,
        iban: String,
    })

    return (
        <View style={styles.container}>
            <View>
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
                    userError({
                        description: 'A descrição não pode ser vazio'
                    })
                    return
                }
                if (addChatGroupRequest.ibans[0] == '') {
                    userError({
                        iban: 'O iban não pode ser vazio'
                    })
                    return
                }
                try {
                    await api.post("/api/v1/chatgroups", addChatGroupRequest,
                        {
                            headers: {
                                Authorization: 'Bearer ' + session
                            },
                        }
                    );
                } catch (errors) {
                    if (errors.status == 'NOT_FOUND') {
                        Alert.alert(
                            'Erro',
                            errors.message
                        )
                        return
                    }
                    useError(error.response.data)
                }

            }}>
                <Text style={styles.button.text}>Adicionar Converça</Text>
            </Pressable>
        </View>
    )
}