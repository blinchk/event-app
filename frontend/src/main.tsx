import React from 'react'
import ReactDOM from 'react-dom/client'

import App from "./App.tsx";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import AddEventPage from "./routes/EventPage/AddEventPage/AddEventPage.tsx";
import EventPage, {loader as eventLoader} from "./routes/EventPage/EventPage.tsx";
import HomePage from "./routes/HomePage/HomePage.tsx";

const router = createBrowserRouter([
    {
        path: '/',
        element: <App />,
        id: 'root',
        children: [
            {
                index: true,
                element: <HomePage/>
            },
            {
                path: '/event/add',
                element: <AddEventPage />,
                id: 'add-event-page'
            },
            {
                path: '/event/:id',
                element: <EventPage />,
                id: 'event-page',
                loader: eventLoader,
            },
        ]
    }
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
      <RouterProvider router={router}/>
  </React.StrictMode>
)
