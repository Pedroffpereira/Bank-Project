import api from "@/Configuration/api";
import { useSession } from "@/app/context/ctx";
import ChatCard from "@/components/Pages/app/chat/ChatCard";
import { View, Text } from "@/components/Themed";
import AntDesign from '@expo/vector-icons/AntDesign';
import { StyleSheet, Animated, Pressable } from "react-native";
import { styles } from "@/components/styles/app/styles";
import { useEffect, useState } from "react";

export default function ChatGroupsScreen() {
    const { session } = useSession();
    const style = StyleSheet.create({
        container: {

            backgroundColor: '#00559a',

            flexDirection: 'row',
        },
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
        },
        left: {
            marginRight: 'auto'
        },

        right: {
            marginLeft: 'auto'
        }
    })
    const [groups, setGroups] = useState([]);
    const [page, setPage] = useState(0);
    const size = 4;
    async function getGroups() {
        const chatGroups = await api.get("/api/v1/chatgroups?size=" + size + "&page=" + page, {
            headers: {
                Authorization: 'Bearer ' + session
            }
        })
        setGroups(chatGroups.data);
    }
    
    getGroups();
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
            <View style={style.container}>
                {
                    page != 0 ? (
                        <Pressable style={style.left} onPress={async () => {
                            setPage(page - 1);
                            await getGroups();
                        }}>
                            <AntDesign name="arrowleft" size={24} color="#fff" />
                        </Pressable>
                    ) : (
                        <View></View>
                    )
                }
                {
                    groups.length >= size ? (<Pressable style={style.right} onPress={async () => {
                        setPage(page + 1);
                        await getGroups();
                    }}>
                        <AntDesign name="arrowright" size={24} color="#fff" />
                    </Pressable>) : (<View></View>)
                }
            </View>
        </View>
    )
}