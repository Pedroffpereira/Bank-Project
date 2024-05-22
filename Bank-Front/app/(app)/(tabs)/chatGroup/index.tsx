import api from "@/Configuration/api";
import { useSession } from "@/app/context/ctx";
import ChatCard from "@/components/Pages/app/chat/ChatCard";
import { View, Text } from "@/components/Themed";

import { StyleSheet, Animated } from "react-native";
import { styles } from "@/components/styles/app/styles";
import { useEffect, useState } from "react";

export default function ChatGroupsScreen() {
    const { session } = useSession();
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
    const [groups, setGroups] = useState([]);
    useEffect(() => {
        async function getGroups() {
            const chatGroups = await api.get("/api/v1/chatgroups?size=4", {
                headers: {
                    Authorization: 'Bearer ' + session
                }
            })
            setGroups(chatGroups.data);
        }
        getGroups();
    }, []);
    return (
        <View style={styles.container}>
            <Text style={style.title}>Lista de converças</Text>
            {
                groups.length > 0 ? groups.map((group, index) => (
                    <View key={index} style={style.card}>
                        <ChatCard group={group} />
                    </View>
                )) : (
                    <Text style={style.text}>Ainda não tem salas de chat</Text>
                )
            }

        </View>
    )
}