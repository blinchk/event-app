import {Container} from "react-bootstrap";
import AddEventForm from "../../components/event/AddEventForm/AddEventForm.tsx";

const AddEventPage = () => (
    <Container>
        <h1>Ürituse lisamine</h1>
        <AddEventForm />
    </Container>
);

export default AddEventPage;