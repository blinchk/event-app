import {Button, Form} from "react-bootstrap";

const AddEventForm = () => (
    <Form>
        <Form.Group>
            <Form.Label>Ürituse nimi</Form.Label>
            <Form.Control></Form.Control>
        </Form.Group>
        <Form.Group>
            <Form.Label>Toimumisaeg</Form.Label>
            <Form.Control></Form.Control>
        </Form.Group>
        <Form.Group>
            <Form.Label>Toimumise koht</Form.Label>
            <Form.Control></Form.Control>
        </Form.Group>
        <Form.Group>
            <Form.Label>Lisainfo</Form.Label>
            <Form.Control as="textarea"></Form.Control>
        </Form.Group>

        <Button variant="secondary">
            Tagasi
        </Button>

        <Button variant="primary" type="submit">
            Salvesta
        </Button>
    </Form>
)

export default AddEventForm;