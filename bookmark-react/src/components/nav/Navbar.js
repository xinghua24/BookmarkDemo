import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Container from "@material-ui/core/Container";
import { Link } from "@material-ui/core";

function Navbar() {
  const useStyles = makeStyles((theme) => ({
    root: {
      flexGrow: 1,
    },
    toolbar: {
      paddingLeft: 0,
    },
    about: {
      position: 'relative',
      width: '100%',
      marginLeft: theme.spacing(3),
    },
    menuButton: {
      marginRight: theme.spacing(2),
    },
    title: {
      flexGrow: 1,
    },
    container: {
      paddingTop: theme.spacing(4),
      paddingBottom: theme.spacing(4),
    },
  }));
  const classes = useStyles();

  return (
    <AppBar position="static">
      <Container maxWidth="lg">
        <Toolbar className={classes.toolbar}>
          <Typography variant="h5" className={classes.title}>
            <Link href="/" color="inherit" underline="none">
              Bookmarks
            </Link>
          </Typography>
          <div className={classes.about}>
          <Link variant="h6" href="/about" color="inherit" underline="none">
            About
          </Link>
          </div>
        </Toolbar>
      </Container>
    </AppBar>
  );
}

export default Navbar;
