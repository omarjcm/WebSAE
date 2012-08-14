/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.informacion;

/**
 *
 * @author Guillermo Pizarro
 */
public class Lenguaje {
    public static int TOTAL = 3;
    
    public static int ES = 0;
    public static int EN = 1;
    public static int PT = 2;

    public static String LANG_ES = "es";
    public static String LANG_EN = "en";
    public static String LANG_PT = "pt";
    
    public static int parse(String lang) {
        if (lang.compareTo("en") == 0) {
            return EN;
        } else if (lang.compareTo("pt") == 0) {
            return PT;
        }
        return ES;
    }

    /* MENSAJES DE ERROR */

    public static String[] ERROR_AGENDA_REPETIDO = {"La Fecha a ingresar, ya se encuentra registrada.",
                                                   "The Date to enter, is already registered.",
                                                   "La Fecha para assinar, já está registrado."};

    public static String[] ERROR_ACTIVIDAD_REPETIDA = {"La Actividad a ingresar, ya se encuentra registrada a esa hora.",
                                                       "The Activity to enter, is already registered in that time.",
                                                       "La Actividad para assinar, já está registrado a esa hora."};

    public static String[] ERROR_ACTIVIDAD_TRASLAPADA = {"La Actividad a ingresar, esta siendo traslapada con otra actividad ya ingresada.",
                                                         "The Activity to enter, being overlapped with other activity already registered.",
                                                         "La Actividad para assinar, ser sobreposta com as outra actividad já registrado."};

    public static String[] ERROR_AUSPICIANTE_REPETIDO = {"El Auspiciante a ingresar, ya se encuentra registrada.",
                                                         "The Sponsor to enter, is already registered.",
                                                         "El Patrocinador para assinar, já está registrado."};

    public static String[] ERROR_CARGO_REPETIDO = {"El Cargo a ingresar, ya se encuentra registrada.",
                                                   "The Position to enter, is already registered.",
                                                   "El Cargo para assinar, já está registrado."};

    public static String[] ERROR_CARGO_ASIGNADO = {"El Cargo a eliminar, se encuentra asignado.",
                                                   "The charge to remove, is assigned.",
                                                   "A taxa para retirar, é atribuído."};
    
    public static String[] ERROR_CATEGORIA_REPETIDO = {"La Categoría a ingresar, ya se encuentra registrada.",
                                                       "The Category to enter, is already registered.",
                                                       "La Categoria para assinar, já está registrado."};

    public static String[] ERROR_CLAVE_MODIFICAR = {"La contrasena actual ingresa no coincide con la contraseña actual almacenada. La contrasena no ha sido modificada.",
                                                    "The current password entered does not match the current password stored. The password was not changed.",
                                                    "O actual senha introduzida não corresponder ao actual senha armazenada. A senha não foi alterada."};

    public static String[] ERROR_CONFERENCISTA_REPETIDO = {"El Conferencista a ingresar, ya se encuentra registrada.",
                                                           "The Speaker to enter, is already registered.",
                                                           "Do Palestrante para assinar, já está registrada."};

    public static String[] ERROR_DESCUENTO_REPETIDO = {"El Descuento a ingresar, ya se encuentra registrada.",
                                                       "The Discount to enter, is already registered.",
                                                       "Do Descuento para assinar, já está registrada."};

    public static String[] ERROR_DESCUENTO_TRASLAPADO = {"El Descuento a ingresar, esta siendo traslapada con otro descuento ya ingresado.",
                                                         "The Discount to enter, being overlapped with other discount already registered.",
                                                         "Do Descuento para assinar, ser sobreposta com as outro descuento já registrado."};

    public static String[] ERROR_EMAIL_CLAVE = {"La dirección de correo electrónico o la contraseña son incorrectas. Inténtelo de nuevo.",
                                                "The email address or password are incorrect. Please try again.",
                                                "O endereço de email ou a senha estão incorretas. Por favor, tente novamente."};

    public static String[] ERROR_EMAIL_ENVIO = {"Hubo problemas en el envío del correo, pero Ud. ha sido registrado.",
                                                "There were problems sending the email, but you have been registered.",
                                                "Não houve problemas para enviar e-mail, mas você tem sido registada."};

    public static String[] ERROR_EMAIL_NOENVIADO = {"No se pudo enviar el correo, por favor intente más tarde.",
                                                    "Unable to send email, please try again later.",
                                                    "Não foi possível enviar o e-mail, tente novamente mais tarde."};

    public static String[] ERROR_EMAIL_NOREGISTRADO = {"El correo ingresado no se encuentra registrado.",
                                                       "The entered email is not registered.",
                                                       "Os inscritos e-mail não está registrado."};

    public static String[] ERROR_EMAIL_REPETIDO = {"Ese correo ya se encuentra registrado.",
                                                   "That email is already registered.",
                                                   "Esse e-mail já está registrado."};

    public static String[] ERROR_EMPRESA_REGISTRAR = {"Hubo problemas en el registro de la Empresa/Universidad. Intente más tarde.",
                                                      "There were problems with the registration of the Enterprise/University. Try again later.",
                                                      "Houve problemas com o registro da Empresa/Universidade. Tente novamente mais tarde."};

    public static String[] ERROR_EMPRESA_REPETIDA = {"La Empresa/Universidad a ingresar, ya se encuentra registrada.",
                                                     "The Enterprise/University to enter, is already registered.",
                                                     "A Empresa/Universidade para assinar, já está registrado."};

    public static String[] ERROR_EVENTO_REPETIDO = {"El Evento a ingresar, ya se encuentra registrado.",
                                                    "The Event to enter, is already registered.",
                                                    "Do Evento para assinar, já está registrado."};

    public static String[] ERROR_GRUPO_INVESTIGACION_REPETIDO = {"El Grupo de Investigación a ingresar, ya se encuentra registrada.",
                                                                 "The Rearch Group to enter, is already registered.",
                                                                 "Da Grupo de Pesquisa para assinar, já está registrado."};

    public static String[] ERROR_GRUPO_INVESTIGACION_ASIGNADO = {"El Grupo de Investigación a eliminar, se encuentra asignado en algun Evento.",
                                                                 "The Rearch Group to delete, is assigned to some Event.",
                                                                 "Da Grupo de Pesquisa para retirar, é atribuído do Evento."};

    public static String[] ERROR_MATERIAL_REPETIDO = {"El Material a ingresar, ya se encuentra registrada.",
                                                      "The Material to enter, is already registered.",
                                                      "El Material para assinar, já está registrado."};

    public static String[] ERROR_MENU_OPCIONES_MODIFICAR = {"No se pudo modificar el Menú de Opciones.",
                                                            "Unable to change the options menu.",
                                                            "ão é possível alterar o menu de opções."};

    public static String[] ERROR_ORGANIZADOR_REPETIDO = {"El Organizador a ingresar, ya se encuentra registrada.",
                                                         "The Organizer to enter, is already registered.",
                                                         "Do Organizador para assinar, já está registrada."};

    public static String[] ERROR_PRECIO_REPETIDO = {"El Precio a ingresar, ya se encuentra registrada.",
                                                    "The Price to enter, is already registered.",
                                                    "Do Precio para assinar, já está registrada."};

    public static String[] ERROR_PRECIO_TRASLAPADO = {"El Precio a ingresar, esta siendo traslapada con otro precio ya ingresado.",
                                                      "The Price to enter, being overlapped with other price already registered.",
                                                      "Do Precio para assinar, ser sobreposta com as outro precio já registrado."};

    public static String[] ERROR_SUBCATEGORIA_REPETIDA = {"La SubCategoria a ingresar, ya se encuentra registrada.",
                                                          "The SubCategory to enter, is already registered.",
                                                          "Da SubCategoria para assinar, já está registrada."};

    public static String[] ERROR_SUBCATEGORIA_ASIGNADA = {"La SubCategoria no se puede eliminar debido a que tiene asignado varios elementos.",
                                                          "The sub can not be deleted because it has assigned several elements.",
                                                          "O sub não pode ser excluído porque tem afectado vários elementos."};
    
    public static String[] ERROR_SUBEVENTO_REPETIDO = {"El SubEvento a ingresar, ya se encuentra registrado.",
                                                       "The SubEvent to enter, is already registered.",
                                                       "Do SubEvento para assinar, já está registrado."};

    public static String[] ERROR_TECNICO_PROBLEMAS = {"Existen problemas técnicos, intente más tarde.",
                                                      "There are technical problems, please try again later.",
                                                      "Existem problemas técnicos, por favor, tente novamente mais tarde."};
    
    public static String[] ERROR_TECNICO_REGISTRO = {"Hubo problemas en el registro del Usuario. Intente más tarde.",
                                                      "There were problems with the registration of the user. Try again later.",
                                                      "Houve problemas com o registo do utilizador. Tente novamente mais tarde."};

    public static String[] ERROR_TIPO_EMPRESA_REPETIDA = {"El Tipo de Empresa a ingresar, ya se encuentra registrada.",
                                                          "The Enterprise Type to enter, is already registered.",
                                                          "A Tipo de Empresa para assinar, já está registrado."};

    public static String[] ERROR_TIPO_EVENTO_REPETIDO = {"El Tipo de Evento a ingresar, ya se encuentra registrada.",
                                                         "The Event Type to enter, is already registered.",
                                                         "El Tipo de Evento para assinar, já está registrado."};

    public static String[] ERROR_TIPO_MATERIAL_REPETIDO = {"El Tipo de Material a ingresar, ya se encuentra registrada.",
                                                           "The Material Type to enter, is already registered.",
                                                           "El Tipo de Material para assinar, já está registrado."};

    public static String[] ERROR_TITULO_REPETIDO = {"El Título a ingresar, ya se encuentra registrado.",
                                                   "The Degree to enter, is already registered.",
                                                   "El Titulo para assinar, já está registrado."};

    public static String[] ERROR_ASIGNADO_EMPRESA = {"No se pudo eliminar, debido a que se encuentra asignado a alguna Universidad/Empresa.",
                                                     "Could not removed, because is assigned to an University/Enterprise.",
                                                     "Poderá não ser removida, pois é atribuído a um Universidad/Empresa."};

    public static String[] ERROR_ASIGNADO_EVENTO = {"No se pudo eliminar, debido a que se encuentra asignado a algún Evento.",
                                                    "Could not removed, because is assigned to an Event.",
                                                    "Poderá não ser removida, pois é atribuído a um evento."};

    public static String[] ERROR_ASIGNADO_USUARIO = {"No se pudo eliminar, debido a que se encuentra asignado a algún Usuario.",
                                                     "Could not removed, because is assigned to an User.",
                                                     "Poderá não ser removida, pois é atribuído a um Usuario."};

    public static String[] ERROR_TEMA_REPETIDO = {"El Tema ingresado ya se encuentra registrado.",
                                                  "The Item is already registered input.",
                                                  "O ponto de entrada já está registrado."};
    
    public static String[] ERROR_TEMA_ASIGNADO = {"No se pudo eliminar, debido a que se encuentra asignado a un artículo.",
                                                  "Could not delete because it is assigned to an item.",
                                                  "Não foi possível eliminar porque é atribuída a um item."};
    
    public static String[] ERROR_SECCION_REPETIDO = {"La Sección ingresada ya se encuentra registrada.",
                                                     "The Section entered is already registered.",
                                                     "A Secção entrou já está registrado."};

    public static String[] ERROR_SECCION_ASIGNADO = {"No se pudo eliminar, debido a que se encuentra asignado a una pregunta.",
                                                     "Could not delete because it is assigned to a question.",
                                                     "Não foi possível eliminar, porque ele é atribuído a uma causa."};
    
    public static String[] ERROR_PREGUNTA_REPETIDO = {"La Pregunta ingresada ya se encuentra registrada.",
                                                      "The Question entered is already registered.",
                                                      "A Pergunta entrou já está registrado."};

    public static String[] ERROR_PREGUNTA_ASIGNADO = {"No se pudo eliminar, debido a que se encuentra asignado a una respuesta.",
                                                      "Could not delete because it is assigned to an answer.",
                                                      "Não foi possível eliminar, porque ele é atribuído a uma resposta."};

    public static String[] ERROR_REGISTRO_REPETIDO = {"Ya se encuentra registrado a este evento.",
                                                      "Already register to this Event.",
                                                      "Já está registrado a este Evento."};

    public static String[] ERROR_ALTERNATIVA_REPETIDO = {"La Alternativa ingresada ya se encuentra registrada.",
                                                         "Alternative entered is already registered.",
                                                         "Alternativa entrou já está registrado."};

    public static String[] ERROR_AUTOR_ARTICULO_REPETIDO = {"El email se encuentra repetido.",
                                                            "The email is recorded.",
                                                            "O e-mail é registrado."};

    public static String[] ERROR_AUTOR_ARTICULO_NO_EXISTE = {"El email del autor que desea registrar no se encuentra en el Sistema.",
                                                             "Author's email you want to register is not found in the System.",
                                                             "Author's e-mail você deseja registrar não está no sistema."};

    public static String[] ERROR_ARTICULO_REPETIDO = {"El título del artículo ya se encuentra registrado.",
                                                      "The article title is already registered.",
                                                      "O título do artigo já está registrado."};

    /* MENSAJES DE OK */
    public static String[] OK_ALTERNATIVA_REGISTRAR = {"Registro exitoso de la Alternativa.",
                                                       "Successful registration of Alternative.",
                                                       "O êxito do registo Alternativa."};
    
    public static String[] OK_ALTERNATIVA_MODIFICAR = {"Modificación exitosa de la Alternativa.",
                                                       "Successful modification of Alternative.",
                                                       "Successful modificação da Alternativa."};
    
    public static String[] OK_ALTERNATIVA_ELIMINAR = {"Eliminación exitosa de la Alternativa.",
                                                      "Successful elimination of the Alternative.",
                                                      "Sucesso eliminação da Alternativa."};
    
    public static String[] OK_PREGUNTA_REGISTRAR = {"Registro exitoso de la Pregunta.",
                                                    "Registration successful Question.",
                                                    "Registo êxito Pergunta."};

    public static String[] OK_PREGUNTA_MODIFICAR = {"Modificación exitosa de la Pregunta.",
                                                    "Successful modification of the Question.",
                                                    "Successful modificação da Pergunta."};

    public static String[] OK_PREGUNTA_ELIMINAR = {"Eliminación exitosa de la Pregunta.",
                                                   "Disposal successful Question.",
                                                   "Eliminação êxito Pergunta."};

    public static String[] OK_SECCION_REGISTRAR = {"Registro exitoso de la Sección.",
                                                   "Successful registration of the Section.",
                                                   "O êxito do registo da secção."};

    public static String[] OK_SECCION_MODIFICAR = {"Modificación exitosa de la Sección.",
                                                   "Successful modification of Section.",
                                                   "Successful modificação da secção."};

    public static String[] OK_SECCION_ELIMINAR = {"Eliminación exitosa de la Sección.",
                                                  "Successful elimination of the Section.",
                                                  "Sucesso eliminação da Secção."};

    public static String[] OK_EVALUACION_ACTUALIZAR = {"Actualización exitosa de la Evaluación.",
                                                      "Successful update of the Assessmen.",
                                                      "O êxito do actualizacion da avaliação."};

    public static String[] OK_EVALUACION_REGISTRAR = {"Registro exitoso de la Evaluación.",
                                                      "Successful registration of the Assessmen.",
                                                      "O êxito do registo da avaliação."};
    
    public static String[] OK_EVALUACION_MODIFICAR = {"Modificación exitosa de la Evaluación.",
                                                      "Successful modification of the Assessment.",
                                                      "Successful modificação da Avaliação."};

    public static String[] OK_TEMA_REGISTRAR = {"Registro exitoso del Tema.",
                                                "Successful Track Record.",
                                                "Registro exitoso del Tema."};
    
    public static String[] OK_TEMA_MODIFICAR = {"Modificación exitosa del Tema.",
                                                "Successful modification of the theme.",
                                                "Successful modificação do tema."};

    public static String[] OK_TEMA_ELIMINAR = {"Eliminación exitosa del Tema.",
                                               "Disposal successful theme.",
                                               "Eliminação sucesso tema."};

    public static String[] OK_CONVOCATORIA_REGISTRAR = {"Registro exitoso de la Convocatoria.",
                                                       "Successful registration of the Call.",
                                                       "O êxito do registo de chamadas."};
    
    public static String[] OK_CONVOCATORIA_MODIFICAR = {"Modificación exitosa de la Convocatoria.",
                                                       "Successful modification of the Call.",
                                                       "Successful modificação do convite."};

    public static String[] OK_CONVOCATORIA_ACTUALIZAR = {"Actualización exitosa de la Convocatoria.",
                                                         "Successful Update of the Call.",
                                                         "Successful actualización do convite."};

    public static String[] OK_AGENDA_ELIMINAR = {"Eliminación exitosa de la Fecha.",
                                                 "Successful elimination of Date.",
                                                 "Sucesso eliminação do Fecha."};

    public static String[] OK_AGENDA_MODIFICAR = {"Modificación exitosa de la Fecha.",
                                                  "Successful modification of Date.",
                                                  "Successful modificação do Fecha."};

    public static String[] OK_AGENDA_REGISTRAR = {"Registro exitoso de la Fecha.",
                                                  "Successful registration of Date.",
                                                  "O êxito do registo da Fecha."};

    public static String[] OK_ACTIVIDAD_ELIMINAR = {"Eliminación exitosa de la Actividad.",
                                                    "Successful elimination of Activiry.",
                                                    "Sucesso eliminação do Actividad."};

    public static String[] OK_ACTIVIDAD_MODIFICAR = {"Modificación exitosa de la Actividad.",
                                                     "Successful modification of Activity.",
                                                     "Successful modificação do Actividad."};

    public static String[] OK_ACTIVIDAD_REGISTRAR = {"Registro exitoso de la Actividad.",
                                                     "Successful registration of Activity.",
                                                     "O êxito do registo da Actividad."};

    public static String[] OK_AUSPICIANTE_ELIMINAR = {"Eliminación exitosa del Auspiciante.",
                                                      "Successful elimination of Sponsor.",
                                                      "Sucesso eliminação do Patrocinador."};

    public static String[] OK_AUSPICIANTE_MODIFICAR = {"Modificación exitosa del Auspiciante.",
                                                  "Successful modification of Sponsor.",
                                                  "Successful modificação do Patrocinador."};

    public static String[] OK_AUSPICIANTE_REGISTRAR = {"Registro exitoso del Auspiciante.",
                                                  "Successful registration of Sponsor.",
                                                  "O êxito do registo da Patrocinador."};

    public static String[] OK_ASIGNACIONES_REGISTRAR = {"Registro exitoso de las Asignaciones.",
                                                        "Successful registration of assignments.",
                                                        "O êxito do registo da atribuições."};
    
    public static String[] OK_CARGO_MODIFICAR = {"Modificación exitosa del Cargo.",
                                                  "Successful modification of Position.",
                                                  "Successful modificação do Cargo."};

    public static String[] OK_CARGO_REGISTRAR = {"Registro exitoso del Cargo.",
                                                 "Successful registration of Position.",
                                                 "O êxito do registo da Cargo."};

    public static String[] OK_CARGO_ELIMINAR = {"Eliminación exitosa del Cargo.",
                                                "Successful elimination of Position.",
                                                "Sucesso eliminação da Cargo."};

    public static String[] OK_CATEGORIA_ELIMINAR = {"Eliminación exitosa de la Categoría.",
                                                    "Successful elimination of Category.",
                                                    "Sucesso eliminação do Categoria."};
    
    public static String[] OK_CATEGORIA_MODIFICAR = {"Modificación exitosa de la Categoría.",
                                                     "Successful modification of Category.",
                                                     "Successful modificação do Categoria."};

    public static String[] OK_CATEGORIA_REGISTRAR = {"Registro exitoso del Categoría.",
                                                     "Successful registration of Category.",
                                                     "O êxito do registo da Categoria."};

    public static String[] OK_CLAVE_MODIFICAR = {"La contrasena ha sido modificada exitosamente.",
                                                 "The password has changed successfully.",
                                                 "A senha foi alterada com sucesso."};

    public static String[] OK_CONFERENCISTA_REGISTRAR = {"Registro exitoso del Conferencista.",
                                                         "Successful registration of Speaker.",
                                                         "O êxito do registo do Palestrante."};

    public static String[] OK_CONFERENCISTA_MODIFICAR = {"Modificación exitosa del Conferencista.",
                                                         "Successful modification of Speaker.",
                                                         "O êxito do modificação do Palestrante."};

    public static String[] OK_CONFERENCISTA_ELIMINAR = {"Eliminación exitosa del Conferencista.",
                                                        "Successful elimination of Speaker.",
                                                        "Sucesso eliminação do Palestrante."};

    public static String[] OK_EVALUADOR_REGISTRAR = {"Registro exitoso del Evaluador.",
                                                     "Evaluator Registration successful.",
                                                     "Avaliador de inscrição."};

    public static String[] OK_EVALUADOR_ELIMINAR = {"Eliminación exitosa del Evaluador.",
                                                    "Disposal successful Evaluator.",
                                                    "Eliminação Avaliador de sucesso."};

    public static String[] OK_CUENTA_REGISTRAR = {"La cuenta ha sido registrada exitosamente.",
                                                  "The account was registered successfully.",
                                                  "A conta foi registrada com sucesso."};

    public static String[] OK_CUENTA_MODIFICAR = {"Modificación exitosa del Usuario.",
                                                  "Successful modification of the User.",
                                                  "Successful modificação do Usuário."};

    public static String[] OK_DESCUENTO_REGISTRAR = {"Registro exitoso del Descuento.",
                                                     "Successful registration of Discount.",
                                                     "O êxito do registo do Descuento."};

    public static String[] OK_DESCUENTO_MODIFICAR = {"Modificación exitosa del Descuento.",
                                                     "Successful modification of Discount.",
                                                     "O êxito do modificação do Descuento."};

    public static String[] OK_DESCUENTO_ELIMINAR = {"Eliminación exitosa del Descuento.",
                                                    "Successful elimination of Discount.",
                                                    "Sucesso eliminação do Descuento."};

    public static String[] OK_EMAIL_RECUPERACION = {"Se ha enviado un correo con su contraseña. Por favor, revise su correo.",
                                                    "Was sent an email with your password. Please check your email.",
                                                    "Foi enviado um e-mail com sua senha. Verifique seu e-mail."};

    public static String[] OK_EMPRESA_REGISTRAR = {"Registro exitoso de la Empresa.",
                                                   "Successful registration of Enterprise.",
                                                   "O êxito do registo da Empresa."};

    public static String[] OK_EMPRESA_MODIFICAR = {"Modificación exitosa de la Empresa.",
                                                   "Successful modification of Enterprise.",
                                                   "Successful modificação da Empresa."};

    public static String[] OK_EMPRESA_ELIMINAR = {"Eliminación exitosa de la Empresa.",
                                                  "Successful elimination of Enterprise.",
                                                  "Successful eliminação da Empresa."};

    public static String[] OK_EVENTO_REGISTRAR = {"Registro exitoso del Evento.",
                                                  "Successful registration of the Event.",
                                                  "O êxito do registo da Evento."};

    public static String[] OK_EVENTO_MODIFICAR = {"Modificación exitosa del Evento.",
                                                  "Successful modification of the Event.",
                                                  "O êxito do modificação da Evento."};

    public static String[] OK_FAQ_MODIFICAR = {"Modificación exitosa del FAQ.",
                                               "Successful modification of FAQ.",
                                               "O êxito do modificação da FAQ."};

    public static String[] OK_GRUPO_INVESTIGACION_REGISTRAR = {"Registro exitoso del Grupo de Investigación.",
                                                               "Successful registration of Research Group.",
                                                               "O êxito do registo da Grupo de Pesquisa"};

    public static String[] OK_GRUPO_INVESTIGACION_MODIFICAR = {"Modificación exitosa del Grupo de Investigación..",
                                                               "Successful modification of Resarch Group.",
                                                               "Successful modificação da Grupo de Pesquisa."};

    public static String[] OK_GRUPO_INVESTIGACION_ELIMINAR = {"Eliminación exitosa del Grupo de Investigación.",
                                                              "Successful elimination of Resarch Group.",
                                                              "Successful eliminação da Grupo de Pesquisa"};

    public static String[] OK_MATERIAL_ELIMINAR = {"Eliminación exitosa del Material.",
                                                   "Successful elimination of Material.",
                                                   "Sucesso eliminação de Material."};

    public static String[] OK_MATERIAL_MODIFICAR = {"Modificación exitosa del Material.",
                                                    "Successful modification of Material.",
                                                    "Successful modificação de Material."};

    public static String[] OK_MATERIAL_REGISTRAR = {"Registro exitoso del Material.",
                                                    "Successful registration of Material.",
                                                    "O êxito do registo de Material."};

    public static String[] OK_MENU_OPCIONES_MODIFICAR = {"Modificación exitosa del Menú de Opciones.",
                                                         "Successful modification of Option Menu.",
                                                         "Successful modificação do menu Opções."};

    public static String[] OK_ORGANIZADOR_REGISTRAR = {"Registro exitoso del Organizador.",
                                                       "Successful registration of Organizer.",
                                                       "O êxito do registo do Organizador."};

    public static String[] OK_ORGANIZADOR_MODIFICAR = {"Modificación exitosa del Organizador.",
                                                       "Successful modification of Organizer.",
                                                       "O êxito do modificação do Organizador."};

    public static String[] OK_ORGANIZADOR_ELIMINAR = {"Eliminación exitosa del Organizador.",
                                                      "Successful elimination of Organizer.",
                                                      "Sucesso eliminação do Organizador."};

    public static String[] OK_PRECIO_REGISTRAR = {"Registro exitoso del Precio.",
                                                  "Successful registration of Price.",
                                                  "O êxito do registo do Precio."};

    public static String[] OK_PRECIO_MODIFICAR = {"Modificación exitosa del Precio.",
                                                  "Successful modification of Price.",
                                                  "O êxito do modificação do Precio."};

    public static String[] OK_PRECIO_ELIMINAR = {"Eliminación exitosa del Precio.",
                                                 "Successful elimination of Price.",
                                                 "Sucesso eliminação do Precio."};

    public static String[] OK_REGISTRO_REGISTRAR = {"Registro exitoso al Evento.",
                                                    "Successful registration to Event.",
                                                    "O êxito do registo do Evento."};
    
    public static String[] OK_SUBCATEGORIA_REGISTRAR = {"Registro exitoso de la SubCategoria.",
                                                        "Successful registration of SubCategory.",
                                                        "O êxito do registo da SubCategoria."};

    public static String[] OK_SUBCATEGORIA_MODIFICAR = {"Modificación exitosa del SubCategoria.",
                                                        "Successful modification of the SubCategory.",
                                                        "O êxito do modificação da SubCategoria."};

    public static String[] OK_SUBCATEGORIA_ELIMINAR = {"Eliminación exitosa del SubCategoria.",
                                                       "Successful elimination of the SubCategory.",
                                                       "Sucesso eliminação da SubCategoria."};

    public static String[] OK_SUBEVENTO_REGISTRAR = {"Registro exitoso del SubEvento.",
                                                     "Successful registration of the SubEvent.",
                                                     "O êxito do registo da SubEvento."};

    public static String[] OK_SUBEVENTO_MODIFICAR = {"Modificación exitosa del SubEvento.",
                                                     "Successful modification of the SubEvent.",
                                                     "O êxito do modificação da SubEvento."};

    public static String[] OK_SUBEVENTO_ELIMINAR = {"Eliminación exitosa del SubEvento.",
                                                    "Successful elimination of the SubEvent.",
                                                    "Sucesso eliminação da SubEvent."};

    public static String[] OK_TIPO_EMPRESA_MODIFICAR = {"Modificación exitosa del Tipo de Empresa.",
                                                        "Successful modification of the Enterprise Type.",
                                                        "Successful modificação do Tipo de Empresa."};

    public static String[] OK_TIPO_EMPRESA_REGISTRAR = {"Registro exitoso del Tipo de Empresa.",
                                                        "Successful registration of the Enterprise Type.",
                                                        "O êxito do registo da Tipo de Empresa."};

    public static String[] OK_TIPO_EMPRESA_ELIMINAR = {"Eliminación exitosa del Tipo de Empresa.",
                                                       "Successful elimination of the Enterprise Type.",
                                                       "Sucesso eliminação do Tipo de Empresa."};

    public static String[] OK_TIPO_EVENTO_ELIMINAR = {"Eliminación exitosa del Tipo de Evento.",
                                                      "Successful elimination of the Event Type.",
                                                      "Sucesso eliminação do Tipo de Evento."};

    public static String[] OK_TIPO_EVENTO_MODIFICAR = {"Modificación exitosa del Tipo de Evento.",
                                                       "Successful modification of the Event Type.",
                                                       "Successful modificação do Tipo de Evento."};

    public static String[] OK_TIPO_EVENTO_REGISTRAR = {"Registro exitoso del Tipo de Evento.",
                                                       "Successful registration of the Event Type.",
                                                        "O êxito do registo da Tipo de Evento."};

    public static String[] OK_TIPO_MATERIAL_ELIMINAR = {"Eliminación exitosa del Tipo de Material.",
                                                      "Successful elimination of the Material Type.",
                                                      "Sucesso eliminação do Tipo de Material."};

    public static String[] OK_TIPO_MATERIAL_MODIFICAR = {"Modificación exitosa del Tipo de Material.",
                                                       "Successful modification of the Material Type.",
                                                       "Successful modificação do Tipo de Material."};

    public static String[] OK_TIPO_MATERIAL_REGISTRAR = {"Registro exitoso del Tipo de Material.",
                                                       "Successful registration of the Event Material.",
                                                        "O êxito do registo da Tipo de Material."};

    public static String[] OK_TITULO_MODIFICAR = {"Modificación exitosa del Título.",
                                                  "Successful modification of Degree.",
                                                  "Successful modificação do Titulo."};

    public static String[] OK_TITULO_REGISTRAR = {"Registro exitoso del Título.",
                                                 "Successful registration of Degree.",
                                                 "O êxito do registo da Titulo."};

    public static String[] OK_TITULO_ELIMINAR = {"Eliminación exitosa del Título.",
                                                 "Successful elimination of Degree.",
                                                 "Sucesso eliminação da Titulo."};

    public static String[] OK_ARTICULO_MODIFICAR = {"Ha modificado correctamente su Artículo.",
                                                    "Have successfully modified your article.",
                                                    "Modificou com êxito o seu artigo."};

    public static String[] OK_ARTICULO_REGISTRAR = {"Ha subido correctamente el artículo. Por favor revisar su email.",
                                                    "Your paper has been uploaded successfully. Please check your email.",
                                                    "Artigo enviado com sucesso. Verifique seu e-mail."};

    public static String[] OK_ARTICULO_ELIMINAR = {"Ha eliminado correctamente su Artículo.",
                                                   "Successfully removed the article.",
                                                   "Removido com sucesso o artigo. "};

    
    public static String[] EMAIL_ARTICULO_EVALUADO = {"Resultado de la evaluación de su trabajo en el Evento.",
                                                       "",
                                                       ""};
    
    public static String[] EMAIL_ARTICULO_RECIBIDO = {"Confirmación de trabajo recibido.",
                                                       "",
                                                       ""};

    public static String[] ERROR_EVALUADOR_REPETIDO = {"El Evaluador asignado ya se encuentra registrado en el evento.",
                                                     "The Assessor assigned already registered for the event.",
                                                     "O Assessor atribuído já registrado para o evento."};

    public static String[] ERROR_EVALUADOR_ASIGNADO = {"No se puede eliminar este evaluador debido a que tiene asignado un artículo a evaluar.",
                                                       "Can not delete this evaluator because it has assigned an article to be evaluated.",
                                                       "Não é possível excluir este avaliador porque tem atribuído um artigo a ser avaliado."};
}
