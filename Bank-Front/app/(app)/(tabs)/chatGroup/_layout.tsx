import React from 'react';
import FontAwesome from '@expo/vector-icons/FontAwesome';
import { Link, Redirect, Tabs } from 'expo-router';
import { Pressable, Text, View } from 'react-native';

import Colors from '@/constants/Colors';
import { useColorScheme } from '@/components/useColorScheme';
import { useSession } from '@/app/context/ctx';

// You can explore the built-in icon families and icons on the web at https://icons.expo.fyi/
function TabBarIcon(props: {
  name: React.ComponentProps<typeof FontAwesome>['name'];
  color: string;
}) {
  return <FontAwesome size={28} style={{ marginBottom: -3 }} {...props} />;
}

export default function TabLayout() {
  const colorScheme = useColorScheme();

  const { session, isLoading } = useSession();

  // You can keep the splash screen open, or render a loading screen like we do here.
  if (isLoading) {
    return <Text>Loading...</Text>;
  }

  // Only require authentication within the (app) group's layout as users
  // need to be able to access the (auth) group and sign in again.
  if (!session) {
    // On web, static rendering will stop here as the user is not authenticated
    // in the headless Node process that the pages are rendered in.
    return <Redirect href="/(auth)" />;
  }


  return (
    <Tabs
      screenOptions={{
        tabBarActiveTintColor: Colors[colorScheme ?? 'light'].tint,
        // Disable the static render of the header on web
        // to prevent a hydration error in React Navigation v6.
      }}>
      <Tabs.Screen
        name="index"
        options={{
          title: 'Lista de Conversa',
          tabBarIcon: ({ color }) => <TabBarIcon name="wechat" color={color} />,
          headerShown: false,
          unmountOnBlur: true
        }}
      />

      <Tabs.Screen
        name="AddChat"
        options={{
          title: 'Adicionar Conversa',
          tabBarIcon: ({ color }) => <TabBarIcon name="plus-circle" color={color} />,
          headerShown: false,
          unmountOnBlur: true
        }}
      />
      <Tabs.Screen
        name="[chatGroup]"
        options={{
          title: 'Adicionar conversa',
          tabBarIcon: ({ color }) => <TabBarIcon name="code" color={color} />,
          headerShown: false,
          href: null
        }}
      />
    </Tabs>
  );
}
