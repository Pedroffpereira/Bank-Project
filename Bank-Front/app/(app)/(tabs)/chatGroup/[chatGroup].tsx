import api from "@/Configuration/api";
import UserDTO from "@/DTOs/userDTO";
import { StyleSheet, Animated } from "react-native";
import { useSession } from "@/app/context/ctx";
import MessageCard from "@/components/Pages/app/chat/MessageCard";
import { useLocalSearchParams } from "expo-router";
import { useEffect, useState } from "react";
import { Pressable, Text, TextInput, View } from "react-native";
import { styles } from "@/components/styles/app/styles";
export default function ChatGroupPage() {
    const { chatGroup } = useLocalSearchParams();
    const style = StyleSheet.create({
        messageAction: {
            marginTop: 'auto',
        },
        textButton: {
            color: '#fff',
            textAlign: 'center'
        },
        text: {
            color: '#fff'
        },
        messageInput: {
            backgroundColor: '#fff',
            borderWidth: 1,
            borderRadius: 50,
            padding: 10
        }
    })
    const { session } = useSession();
    const [chatgroup, setChatgroup] = useState({
        messages: [],
        description: '',
        ibans: []
    });
    const [user, setUser] = useState<UserDTO>(new UserDTO);

    const [messageRequest, setMessageRequest] = useState({
        text: ''
    });

    function handleText(text) {
        setMessageRequest({
            text: text
        })
    }
    async function getGroups() {
        const chatGroupMessage = await api.get("/api/v1/chatgroups/" + chatGroup, {
            headers: {
                Authorization: 'Bearer ' + session
            }
        })
        setChatgroup(chatGroupMessage.data);
    }
    let timer = setTimeout(getGroups, 2000);
    useEffect(() => {
        async function getUser() {
            const user = await api.post("/api/v1/users/auth", null, {
                headers: {
                    Authorization: 'Bearer ' + session
                },
            }).catch(function (error) {
                signOut()
                return;
            });
            setUser(user.data)
        }

        getGroups();
        getUser();
    }, []);
    return (
        <View style={styles.container}>
            <View>
                {
                    chatgroup.messages.length > 0 && chatgroup.messages.map((message, index) => (

                        message.iban == user.iban ? <MessageCard key={index} backgroundColor="#ddf" message={message} /> : <MessageCard key={index} backgroundColor="rgb(234, 240, 246)" message={message} />
                    ))
                }
            </View>
            <View style={
                style.messageAction
            }>
                <Text style={style.text}>Mensagem</Text>
                <TextInput style={style.messageInput} onChangeText={(text) => handleText(text)} value={messageRequest.text} />
                <View>
                    <Pressable style={styles.button} onPress={async () => {
                        await api.post("/api/v1/chatgroups/" + chatGroup + "/message", messageRequest,
                            {
                                headers: {
                                    Authorization: 'Bearer ' + session
                                },
                            }
                        );
                        setMessageRequest({
                            text: ''
                        })
                    }}>
                        <Text style={style.textButton}>Enviar Mensagem</Text>
                    </Pressable>
                </View>
            </View>
        </View>
    )

}