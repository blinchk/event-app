import {Container, Nav, Navbar} from "react-bootstrap";
import { LinkContainer } from 'react-router-bootstrap';

import logo from '../../assets/logo.svg'
import './NavBar.scss';

const NavBar = () => (
    <Navbar>
        <Container>
            <Navbar.Brand>
                <img src={logo} alt="Logo"/>
            </Navbar.Brand>
            <Navbar.Collapse>
                <Nav className="nav-links">
                    <LinkContainer to='/'>
                        <Nav.Link className="nav-links__link">Avaleht</Nav.Link>
                    </LinkContainer>
                    <LinkContainer to='/event/add'>
                        <Nav.Link className="nav-links__link">Ürituse lisamine</Nav.Link>
                    </LinkContainer>
                </Nav>
            </Navbar.Collapse>
        </Container>
    </Navbar>
)

export default NavBar;