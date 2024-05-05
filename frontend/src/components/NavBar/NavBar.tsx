import {Container, Nav, Navbar, NavItem} from "react-bootstrap";
import { LinkContainer } from 'react-router-bootstrap';

import logo from '../../assets/logo.svg'
import symbol from '../../assets/symbol.svg';
import './NavBar.scss';

const NavBar = () => (
    <Navbar className="mb-3">
        <Container>
            <Navbar.Brand>
                <img src={logo} alt="Logo"/>
            </Navbar.Brand>
            <Navbar.Collapse>
                <Nav className="nav-links">
                    <LinkContainer to="/" className="nav-links__link">
                        <NavItem className="nav-link nav-links__link-text">Avaleht</NavItem>
                    </LinkContainer>
                    <LinkContainer to="/event/add" className="nav-links__link">
                        <NavItem className="nav-link nav-links__link-text">Ürituse lisamine</NavItem>
                    </LinkContainer>
                </Nav>
            </Navbar.Collapse>
            <Navbar.Collapse className="justify-content-end">
                <img src={symbol} alt=""/>
            </Navbar.Collapse>
        </Container>
    </Navbar>
)

export default NavBar;